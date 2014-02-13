package com.github.sionin.servlet.guice;

import com.github.sionin.servlet.MyServlet1;
import com.google.inject.*;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.MyGuiceFilter;
import com.google.inject.servlet.ServletModule;

import static com.google.inject.Scopes.SINGLETON;

import javax.servlet.ServletContextEvent;

public class MyGuiceContextListener extends GuiceServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        super.contextDestroyed(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        Module myModyle = new AbstractModule() {
            @Override
            protected void configure() {

                //!!!
                requestStaticInjection(MyGuiceFilter.class);


                bind(MyServlet1.class).in(SINGLETON);
            }
        };

        return Guice.createInjector(myModyle, new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/*").with(MyServlet1.class);
            }
        });
    }

}
