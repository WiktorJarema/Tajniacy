/*
package org.tajniacy.app;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.tajniacy.config.AppConfig;
import org.tajniacy.listener.NicknameSessionListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // trzy podstawowe
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // skopiowane z neta, niepotrzebne, ale niech zostanie na razie
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[] { new HiddenHttpMethodFilter() };
//    }


    // skopiowane z neta, ma pomóc w rozwiązaniu problemu z SessionListenerem
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        servletContext.addListener(new NicknameSessionListener());
//    }

}
*/
