package com.appEv.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



import com.appEv.dao.UserDao;
import com.appEv.dao.UserDaoImpl;
import com.appEv.models.User;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  private UserDao userDao = new UserDaoImpl();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Afficher la page d'inscription
	        request.getRequestDispatcher("/inscription.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	  
	        // Créer un nouvel utilisateur
	        User newUser = new User();
	        newUser.setUsername(username);
	        newUser.setPassword(password);
            newUser.setActive(true);
	        newUser.setEmail(email);
	       
	        // Ajouter l'utilisateur à la base de données
	        userDao.addUser(newUser);

	        // Rediriger vers la page de connexion
	        response.sendRedirect("/AppEv/login.html");
	    }
	}


