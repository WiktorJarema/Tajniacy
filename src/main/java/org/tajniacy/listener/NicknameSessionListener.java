package org.tajniacy.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.GameTable;
import org.tajniacy.model.Nickname;
import org.tajniacy.service.GameTableService;
import org.tajniacy.service.NicknameService;
import org.tajniacy.service.impl.GameTableServiceImpl;
import org.tajniacy.service.impl.NicknameServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
    Bardzo ważna notatka dotycząca korzystania z warstwy serwisowej, innymi słowy z różnych beanów, w session listenerze.

    Taki kod:
    @Autowired
    NicknameService nicknameService;
    nie zadziała. Deklarując listenera w web.xml
    <listener>
        <listener-class>org.tajniacy.listener.NicknameSessionListener</listener-class>
    </listener>

    lub za pomoca adnotacji @WebListener zadajemy contextowi Springa inicjalizację i rejestrację jako Listenera,
    ale wtedy taki obiekt jest tworzony przez servlet poza Springiem, nie jest zarządzany przez context Springa i nie mozna do niego niczego powiązać (Autowire).
    Z kolei gdyby oznaczyć NicknameSessionListener wtedy automatyczne powiązanie zadziała, ale taki bean nie zostanie
    zarejestrowany w context jako Listener. Nie można mieć obu tych rzeczy.

    Rozwiązaniem jest pobranie potrzebnego beana z contextu Sprigna bezpośrednio w metodzie Listenera, za pomocą
    WebApplicationContextUtils.
    Aby to było możliwe, ważna jest kolejność ładowania listenerów.
    ContextLoaderListener musi byc załadowany przed ServletContextListener (to make your web application aware of the Spring context loader)

    Kolejność taką można zrealizować poprzez konfigurację przez web.xml.
    Możliwa jest też realizacja kolejnośc z wykorzystaniem programowej konfiguracji Springa (programmatic configuration, Java config),
    wtedy bez użycia adnotacji @WebListeer (adnotacja @Order, która może mogłaby pomóc u mnie nie zadziałała),
    Listener dodawany jest za pomoca metody addListener (dostępna od Servlet 3) w metody onStartup
    klasy inicjalizującej - roszerzającej WebApplicationInitializer.

    Korzystając z ServletContainerInitializer lub WebApplicationInitializer samodzielnie inicjalizujemy i rejestrujemy
    ServletContextListener.

    Nastepujący kod w metodzie onStartum rozwiązuje sprawę.
    servletContext.addListener(new ContextLoaderListener(rootContext));
    servletContext.addListener(NicknameSessionListener.class);


    Źródła:
    https://stackoverflow.com/questions/20476760/dependency-injection-inside-of-httpsessionlistener-implementation
    https://stackoverflow.com/questions/17656046/autowired-in-servletcontextlistener
    https://stackoverflow.com/questions/38795287/webapplicationcontextutils-getwebapplicationcontextservletcontext-returns-null
*/

@CrossOrigin
//@WebListener
//@Order(Ordered.LOWEST_PRECEDENCE)
public class NicknameSessionListener implements HttpSessionListener {

//    @Autowired
//    NicknameService nicknameService;


    private static AtomicInteger activeSessions = new AtomicInteger();

    public static AtomicInteger getActiveSessions() {
        return activeSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        activeSessions.incrementAndGet();

        HttpSession session = httpSessionEvent.getSession();
        // atrybut jeszcze nie został przypisany
        Nickname nickname = (Nickname) session.getAttribute("nickname");

        System.out.println("Utworzono nową sesję, id: " + session.getId());
        System.out.println("Liczba aktywnych sesji: " + activeSessions);
        System.out.println();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        activeSessions.decrementAndGet();

        HttpSession session = httpSessionEvent.getSession();
        Nickname nickname = (Nickname) session.getAttribute("nickname");

        System.out.println("Koniec sesji dla użytkownika: " + nickname.getName());

        ApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(httpSessionEvent.getSession().getServletContext());
        NicknameService nicknameService = rootContext.getBean(NicknameServiceImpl.class);
        GameTableService gameTableService = rootContext.getBean(GameTableServiceImpl.class);

        // usunięcie gracza ze wszystkich stołów, trochę siłowo, do zmiany w przyszłości
        Long playerId = nickname.getId();
        List<GameTable> gameTables = gameTableService.findAllGameTables();
        for (GameTable gameTable : gameTables) {
            gameTableService.deletePlayerFromGameTable(gameTable.getName(), 1l, playerId);
            gameTableService.deletePlayerFromGameTable(gameTable.getName(), 2l, playerId);
            gameTableService.deletePlayerFromGameTable(gameTable.getName(), 3l, playerId);
            gameTableService.deletePlayerFromGameTable(gameTable.getName(), 4l, playerId);
        }

        // zwolnienie nickname
        try {
            nicknameService.setNicknameIsFree(nickname.getId(), true);
        } catch (NicknameNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("Liczba aktywnych sesji: " + activeSessions);
        System.out.println();
    }

}