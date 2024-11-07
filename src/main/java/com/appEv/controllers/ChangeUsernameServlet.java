package com.appEv.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import com.appEv.dao.UserDao;
import com.appEv.dao.UserDaoImpl;
import com.appEv.models.User;

/**
 * Servlet implementation class ChangeUsernameServlet
 */
@WebServlet("/changeUsername")
public class ChangeUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  private UserDao userDao;

	    @Override
	    public void init() throws ServletException {
	        super.init();
	        userDao = new UserDaoImpl(); // Initialisation de l'objet UserDao (vous devrez peut-être ajuster la création en fonction de votre code)
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	    User user = (User) request.getSession().getAttribute("user");
	    	    request.setAttribute("user", user);
	    	    request.setAttribute("userId", user.getId()); // Ajoutez cette ligne pour ajouter l'ID de l'utilisateur à la requête
	    	    request.getRequestDispatcher("/changeUserName.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire de modification de nom d'utilisateur
	    	
	    	String newUsername = request.getParameter("newUsername");
	    	
	    	 int userId = Integer.parseInt(request.getParameter("userId"));
	    	
	    	 User user = userDao.getUserById(userId);

	    	    if (user != null) {
	    	    	System.out.println("Before update - Old username: " + user.getUsername());
	    	       
	    	    	
	    	    	
	    	    	 // Récupérer l'objet utilisateur actuel de la session
	    	        HttpSession session = request.getSession();
	    	        User existingUser = (User) session.getAttribute("user");

	    	        // Récupérer les valeurs actuelles de l'email et du rôle
	    	        String existingEmail = existingUser.getEmail();
	    	        String existingRole = existingUser.getRoleName();
	    	        
	    	        
	    	      // Mettre à jour le nom d'utilisateur
	    	    	user.setUsername(newUsername);
	    	    	
	    	    	// Enregistrez les informations mises à jour dans l'objet utilisateur
	    	        user.setEmail(existingEmail);
	    	        user.setRoleName(existingRole);
	    	    	
	    	    	
	    	        userDao.updateUsername(user);// Utilisez la méthode updateUsername
	    	        
	    	        System.out.println("After update - New username: " + user.getUsername());
	    	   
	    	        
	    	     // Mettre à jour l'objet User en session
	    	       
	    	        session.setAttribute("user", user); // Mettre à jour les données d'utilisateur en session
	    	       
	    	        System.out.println("Updated user: " + user); // Print the updated user object
	    	        System.out.println("Session attributes: " + session.getAttributeNames()); // Print session attributes
	    	
	    	        
	    	        response.sendRedirect(request.getContextPath() + "/profile.jsp");
	    	    } else {
	    	        response.sendRedirect(request.getContextPath() + "/error.jsp");
	    	    }

	    }

		

		
}