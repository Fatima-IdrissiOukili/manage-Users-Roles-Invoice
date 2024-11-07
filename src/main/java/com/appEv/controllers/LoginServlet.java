package com.appEv.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.appEv.dao.RoleDao;
import com.appEv.dao.RoleDaoImpl;
import com.appEv.dao.UserDao;
import com.appEv.dao.UserDaoImpl;
import com.appEv.models.Role;
import com.appEv.models.User;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Début de la requête POST");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        logger.info("Email de l'utilisateur : " + email);

        User user = userDao.authentificateUser(email, password);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId()); // Ajoutez cette ligne pour stocker l'ID d'utilisateur
            
            
            
            int userId = user.getId();
            logger.info("ID de l'utilisateur : " + userId);

            
           int roleId = userDao.getRoleById(userId);
           session.setAttribute("roleId", roleId);
           logger.info("ID du rôle de l'utilisateur : " + roleId);
           user.setRoleId(roleId);
           String roleName = userDao.getRoleName(roleId); // Utilisez la méthode getRoleName ici
           user.setRoleId(roleId);
           user.setRoleName(roleName); // Définissez le nom du rôle dans l'objet User

         //  Cookie userCookie = new Cookie("userEmail", email);
         //  userCookie.setMaxAge(3600); 
         //  response.addCookie(userCookie);
            

            if (roleId == 1) {
                logger.info("Redirection vers la page du profil de l'utilisateur");
                response.sendRedirect("/AppEv/profile.jsp");
            } else if (roleId == 2) {
                logger.info("Redirection vers la page d'administration");
                response.sendRedirect("/AppEv/Admin.jsp");
            } else {
                logger.info("Redirection vers une page d'erreur");
                response.sendRedirect("/AppEv/error.jsp");
            }
        } else {
            logger.info("Authentification échouée, redirection vers la page de connexion");
            response.sendRedirect("/login.html");
        }
    }
}
