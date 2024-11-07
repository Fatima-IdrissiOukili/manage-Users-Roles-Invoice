package com.appEv.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.appEv.dao.InvoiceDao;
import com.appEv.dao.InvoiceDaoImpl;
import com.appEv.dao.UserDao;
import com.appEv.dao.UserDaoImpl;
import com.appEv.models.Invoice;
import com.appEv.models.Invoiceline;
import com.appEv.models.User;

@WebServlet("/invoices")
public class InvoiceController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private InvoiceDao invoiceDao;
    private UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
        invoiceDao = new InvoiceDaoImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        logger.info("Action: " + action);
        logger.info("ID: " + id);

        if (action == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/addInvoice":
                addInvoice(request, response);
                break;
            case "/editInvoice":
                showEditForm(request, response);
                break;
            case "/updateInvoice":
                updateInvoice(request, response);
                break;
            case "/deleteInvoice":
                deleteInvoice(request, response);
                break;
            case "/list":
                getAllInvoices(request, response);
                break;
            default:
                getAllInvoices(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoiceForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Invoice existingInvoice = invoiceDao.getInvoiceById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoiceForm.jsp");
        request.setAttribute("invoice", existingInvoice);
        dispatcher.forward(request, response);
    }

    private void getAllInvoices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start getAllInvoices");
        List<Invoice> listInvoice = invoiceDao.getAllInvoices();

        for (Invoice invoice : listInvoice) {
            logger.info("Invoice ID: " + invoice.getId());

            List<Invoiceline> invoicelines = invoice.getInvoiceLines();
            for (Invoiceline invoiceline : invoicelines) {
                logger.info("Invoiceline Description: " + invoiceline.getDescription());
            }
        }

        request.setAttribute("listInvoice", listInvoice);

        RequestDispatcher dispatcher = request.getRequestDispatcher("invoiceView.jsp");
        dispatcher.forward(request, response);
        logger.info("Successfully retrieved and forwarded invoice list.");
    }
    
    
    private void updateInvoice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start updateInvoice");

        try {
        	int userId = Integer.parseInt(request.getParameter("user_id"));
        	System.out.println("User ID from request: " + userId);
            String invoiceNumber = request.getParameter("invoiceNumber");

            List<Invoiceline> invoiceLines = new ArrayList<>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (paramName.startsWith("description")) {
                    String lineId = paramName.substring("description".length());
                    String description = request.getParameter("description" + lineId);
                    String amountStr = request.getParameter("amount" + lineId);
                    BigDecimal amount = new BigDecimal(amountStr);
                    invoiceLines.add(new Invoiceline(description, amount));
                }
            }

            // Utilisez la session ou une autre méthode pour obtenir l'utilisateur actuellement connecté
           // User user = (User) request.getSession().getAttribute("user");
         
            // Récupérez l'objet Invoice que vous éditez depuis la base de données
            Invoice invoiceToEdit = invoiceDao.getInvoiceById(userId);
            

            // Mettez à jour les données de l'invoice avec les nouvelles valeurs
            invoiceToEdit.setInvoiceNumber(invoiceNumber);
            invoiceToEdit.setInvoiceLines(invoiceLines);
            User adminUser = new User();
            adminUser.setId(userId);
            invoiceToEdit.setUser(adminUser);
            invoiceToEdit.getUser().setId(userId);
            System.out.println("User ID updated in invoiceToEdit: " + invoiceToEdit.getUser().getId());
            
            System.out.println("User ID from invoiceToEdit: " + invoiceToEdit.getUser().getId());
           // Invoice invoiceToUpdate = new Invoice(id, invoiceNumber, user, invoiceLines);
            boolean success = invoiceDao.updateInvoice(invoiceToEdit);

            if (success) {
                response.sendRedirect("invoices?action=/list");
                logger.info("Successfully Updated and forwarded the invoice.");
            } else {
                // Gérer l'erreur de mise à jour de la facture ici, par exemple, afficher un message d'erreur
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            logger.error("Error while Updating and forwarding the invoice.", e);
        }
    }

    private void addInvoice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	
        	 int userId = Integer.parseInt(request.getParameter("user_id"));
             User adminUser = new User();
             adminUser.setId(userId);
             
             
            String invoiceNumber = request.getParameter("invoiceNumber");
           
            List<Invoiceline> invoiceLines = new ArrayList<>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (paramName.startsWith("description")) {
                    String lineId = paramName.substring("description".length());
                    String description = request.getParameter("description" + lineId);
                    String amountStr = request.getParameter("amount" + lineId);
                    BigDecimal amount = new BigDecimal(amountStr);
                    invoiceLines.add(new Invoiceline(description, amount));
                }
            }

            // Utilisez la session ou une autre méthode pour obtenir l'utilisateur actuellement connecté
           // User user = (User) request.getSession().getAttribute("user");
           
          
            
            // Créez un objet Invoice avec les données collectées
           Invoice newInvoice = new Invoice(invoiceNumber, adminUser, invoiceLines);

            // Ajoutez la facture à la base de données
            boolean success = invoiceDao.addInvoice(newInvoice);

            if (success) {
                response.sendRedirect("invoices?action=/list");
                logger.info("Successfully Added and forwarded invoice.");
            } else {
                // Gérer l'erreur d'ajout de facture ici, par exemple, afficher un message d'erreur
            
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            logger.error("Error while Adding and forwarding invoice.", e);
        }
    }
    


    private void deleteInvoice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start deleteInvoice");
        int id = Integer.parseInt(request.getParameter("id"));
        invoiceDao.deleteInvoice(id);
        response.sendRedirect("invoices?action=/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
