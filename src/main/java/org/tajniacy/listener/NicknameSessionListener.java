package org.tajniacy.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;
import org.tajniacy.service.NicknameService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
public class NicknameSessionListener implements HttpSessionListener {

    @Autowired
    NicknameService nicknameService;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Nowa sesja utworzona");
        System.out.println(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Początek metody sessionDestroyed");

        HttpSession session = httpSessionEvent.getSession();
        Nickname nicknameToBeFree = (Nickname) session.getAttribute("nickname");

        System.out.println("Koniec używania " + nicknameToBeFree.getName());

        // dla testów - też się nie wykonuje
        List<Nickname> list = nicknameService.getAllNicknames();
        System.out.println(list.get(3).getName());

        // ten kod się nie wykonuje
        try {
            nicknameService.setNicknameIsFree(nicknameToBeFree.getId(), true);
        } catch (NicknameNotFoundException exception) {
            exception.printStackTrace();
        }


        System.out.println("Koniec metody sessionDestroyed");
    }

}
