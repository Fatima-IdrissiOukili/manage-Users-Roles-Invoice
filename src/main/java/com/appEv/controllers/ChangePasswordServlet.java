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

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl(); // Initialisez l'objet UserDao
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId"); // Récupérer la valeur brute du paramètre
        System.out.println("userIdParam: " + userIdParam); // Afficher la valeur brute pour le débogage
    
        // Convertir la valeur de userIdParam en un entier
        int userId = -1; // Valeur par défaut en cas d'échec de conversion
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            System.out.println("Error converting userIdParam to int: " + e.getMessage());
        }

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = userDao.getUserById(userId);

        if (user != null) {
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(newPassword);
                userDao.updateUserPassword(user);

                response.sendRedirect(request.getContextPath() + "/profile.jsp");
            } else {
                // Rediriger l'utilisateur vers une page d'erreur pour le cas où les mots de passe ne correspondent pas
                response.sendRedirect(request.getContextPath() + "/passwordMismatch.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

