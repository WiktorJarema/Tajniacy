package org.tajniacy.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.tajniacy.config.AppConfig2;
import org.tajniacy.listener.NicknameSessionListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer2 implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig2.class);
        rootContext.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(NicknameSessionListener.class);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

    }


}

