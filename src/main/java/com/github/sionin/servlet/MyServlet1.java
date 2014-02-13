package com.github.sionin.servlet;

import com.google.inject.Injector;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet1 extends HttpServlet {

    private final Injector injector;

    @Inject
    public MyServlet1(Injector injector) {
        this.injector = injector;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getOutputStream().println("Servlet1" + injector);
    }
}
