package com.ftg.demo.util;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        System.out.println("---> AnnotationConfigWebApplicationContext - web.xml webapp");
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("com.ftg.demo");
        servletContext.addListener(new ContextLoaderListener(root));
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(SpringMvcConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
