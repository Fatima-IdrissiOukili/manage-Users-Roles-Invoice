package com.appEv.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/servletDeconnection")
public class servletDeconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

        if (session != null) {
            // Fermer la session en cours
            session.invalidate();
        }

        // Rediriger vers la page de connexion (ou une autre page appropriée)
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
	}

	

