package com.appEv.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.appEv.dao.InvoiceDao;
import com.appEv.dao.InvoiceDaoImpl;
import com.appEv.models.Invoice;
import com.appEv.models.Invoiceline;

@WebServlet("/userInvoices")
public class UserInvoicesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InvoiceDao invoiceDao;

    @Override
    public void init() throws ServletException {
        super.init();
        invoiceDao = new InvoiceDaoImpl(null); // Initialisez l'objet InvoiceDao
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Ne crée pas de nouvelle session
        
        // Vérifier si l'utilisateur est connecté et si l'ID d'utilisateur est stocké dans la session
        if (session != null) {
            int userId = (int)session.getAttribute("userId");

            // Ajouter un débogage pour afficher l'ID de l'utilisateur dans la console
            System.out.println("UserID from session: " + userId);    
            
            List<Invoice> invoices = invoiceDao.getUserInvoices(userId);
            
            // Calculer le montant total
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (Invoice invoice : invoices) {
                // Ajouter un débogage pour afficher les factures récupérées dans la console
                System.out.println("Invoice: " + invoice.getInvoiceNumber());
                
                for (Invoiceline line : invoice.getInvoiceLines()) {
                    totalAmount = totalAmount.add(line.getAmount());
                }
            }

            // Stocker le montant total en tant qu'attribut de requête
            request.setAttribute("totalAmount", totalAmount);
            
            // Transférer les données à la page JSP
            request.setAttribute("invoices", invoices);
            request.getRequestDispatcher("/mesFactures.jsp").forward(request, response);
        }
    }
}
