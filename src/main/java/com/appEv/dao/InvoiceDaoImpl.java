package com.appEv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.appEv.db.DatabaseConnection;
import com.appEv.models.Invoice;
import com.appEv.models.Invoiceline;
import com.appEv.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceDaoImpl implements InvoiceDao {
    private final Logger logger = LoggerFactory.getLogger(InvoiceDaoImpl.class);

    private final UserDao userDao;
    private final Connection connection;

    public InvoiceDaoImpl(UserDao userDao) {
        this.userDao = userDao;
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        boolean success = false;
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            String insertInvoiceSQL = "INSERT INTO invoice (user_id, invoice_number) VALUES (?, ?)";

            try (PreparedStatement invoiceStmt = connection.prepareStatement(insertInvoiceSQL, Statement.RETURN_GENERATED_KEYS)) {
                invoiceStmt.setInt(1, invoice.getUser().getId());
                invoiceStmt.setString(2, invoice.getInvoiceNumber());

                int affectedRows = invoiceStmt.executeUpdate();

                if (affectedRows > 0) {
                    success = true;
                    try (ResultSet generatedKeys = invoiceStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int invoiceId = generatedKeys.getInt(1);
                            insertInvoiceLines(connection, invoiceId, invoice.getInvoiceLines());
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            logger.error("Erreur lors de l'ajout de la facture", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }

        return success;
    }

    private void insertInvoiceLines(Connection connection, int invoiceId, List<Invoiceline> lines) throws SQLException {
        String insertInvoiceLineSQL = "INSERT INTO invoice_line (invoice_id, description, amount) VALUES (?, ?, ?)";
        try (PreparedStatement lineStmt = connection.prepareStatement(insertInvoiceLineSQL)) {
            for (Invoiceline line : lines) {
                lineStmt.setInt(1, invoiceId);
                lineStmt.setString(2, line.getDescription());
                lineStmt.setBigDecimal(3, line.getAmount());
                lineStmt.executeUpdate();
            }
        }
    }


    @Override
    public boolean updateInvoice(Invoice invoice) {
        boolean success = false;
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            String updateInvoiceSQL = "UPDATE invoice SET invoice_number = ? WHERE id = ?";
            try (PreparedStatement invoiceStmt = connection.prepareStatement(updateInvoiceSQL)) {
                invoiceStmt.setString(1, invoice.getInvoiceNumber());
                invoiceStmt.setInt(2, invoice.getId());
                int affectedRows = invoiceStmt.executeUpdate();

                if (affectedRows > 0) {
                    deleteInvoiceLines(invoice.getId(), connection); // Supprimez les lignes de facture avec l'ID de la facture
                    insertInvoiceLine(connection, invoice.getId(), invoice.getInvoiceLines()); // Insérez les nouvelles lignes de facture
                    success = true;
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            logger.error("Erreur lors de la mise à jour de la facture", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }

        return success;
    }

    private void deleteInvoiceLines(int invoiceId, Connection connection) throws SQLException {
        try (PreparedStatement deleteLinesStmt = connection.prepareStatement("DELETE FROM invoice_line WHERE invoice_id=?")) {
            deleteLinesStmt.setInt(1, invoiceId);
            deleteLinesStmt.executeUpdate();
        }
    }

    private void insertInvoiceLine(Connection connection, int invoiceId, List<Invoiceline> lines) throws SQLException {
        String insertInvoiceLineSQL = "INSERT INTO invoice_line (invoice_id, description, amount) VALUES (?, ?, ?)";
        try (PreparedStatement lineStmt = connection.prepareStatement(insertInvoiceLineSQL)) {
            for (Invoiceline line : lines) {
                lineStmt.setInt(1, invoiceId);
                lineStmt.setString(2, line.getDescription());
                lineStmt.setBigDecimal(3, line.getAmount());
                lineStmt.executeUpdate();
            }
        }
    }


    @Override
    public Invoice getInvoiceById(int id) {
        try {
            String selectInvoiceSQL = "SELECT * FROM invoice WHERE id=?";
            String selectInvoiceLineSQL = "SELECT * FROM invoice_line WHERE invoice_id=?";
            try (PreparedStatement invoiceStmt = connection.prepareStatement(selectInvoiceSQL);
                 PreparedStatement lineStmt = connection.prepareStatement(selectInvoiceLineSQL)) {

                invoiceStmt.setInt(1, id);
                try (ResultSet invoiceResultSet = invoiceStmt.executeQuery()) {
                    if (invoiceResultSet.next()) {
                        Invoice invoice = new Invoice();
                        invoice.setId(invoiceResultSet.getInt("id"));
                        invoice.setInvoiceNumber(invoiceResultSet.getString("invoice_number"));

                        int userId = invoiceResultSet.getInt("user_id");
                        User user = userDao.getUserById(userId);
                        invoice.setUser(user);

                        int invoiceId = invoiceResultSet.getInt("id");
                        lineStmt.setInt(1, invoiceId);
                        try (ResultSet lineResultSet = lineStmt.executeQuery()) {
                            List<Invoiceline> invoiceLines = new ArrayList<>();
                            while (lineResultSet.next()) {
                                Invoiceline line = new Invoiceline();
                                line.setId(lineResultSet.getInt("id"));
                                line.setDescription(lineResultSet.getString("description"));
                                line.setAmount(lineResultSet.getBigDecimal("amount"));
                                invoiceLines.add(line);
                            }
                            invoice.setInvoiceLines(invoiceLines);
                        }
                        return invoice;
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération de la facture par ID", e);
        }
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            String selectAllInvoicesSQL = "SELECT * FROM invoice";
            String selectInvoiceLineSQL = "SELECT * FROM invoice_line WHERE invoice_id=?";
            try (PreparedStatement invoiceStmt = connection.prepareStatement(selectAllInvoicesSQL);
                 PreparedStatement lineStmt = connection.prepareStatement(selectInvoiceLineSQL)) {

                try (ResultSet invoiceResultSet = invoiceStmt.executeQuery()) {
                    while (invoiceResultSet.next()) {
                        Invoice invoice = new Invoice();
                        invoice.setId(invoiceResultSet.getInt("id"));
                        int userId = invoiceResultSet.getInt("user_id");
                        User user = userDao.getUserById(userId);
                        invoice.setUser(user);
                        invoice.setInvoiceNumber(invoiceResultSet.getString("invoice_number"));

                        int invoiceId = invoiceResultSet.getInt("id");
                        lineStmt.setInt(1, invoiceId);
                        try (ResultSet lineResultSet = lineStmt.executeQuery()) {
                            List<Invoiceline> invoiceLines = new ArrayList<>();
                            while (lineResultSet.next()) {
                                Invoiceline line = new Invoiceline();
                                line.setId(lineResultSet.getInt("id"));
                                line.setDescription(lineResultSet.getString("description"));
                                line.setAmount(lineResultSet.getBigDecimal("amount"));
                                invoiceLines.add(line);
                            }
                            invoice.setInvoiceLines(invoiceLines);
                        }

                        invoiceList.add(invoice);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération de la liste de factures", e);
        }
        return invoiceList;
    }

    @Override
    public boolean deleteInvoice(int id) {
        try {
            deleteInvoiceLines(id);
            String deleteInvoiceSQL = "DELETE FROM invoice WHERE id=?";
            try (PreparedStatement deleteInvoiceStmt = connection.prepareStatement(deleteInvoiceSQL)) {
                deleteInvoiceStmt.setInt(1, id);
                int invoiceDeleted = deleteInvoiceStmt.executeUpdate();
                return invoiceDeleted > 0;
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la suppression de la facture", e);
            return false;
        }
    }

    private void deleteInvoiceLines(int invoiceId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement deleteLinesStmt = connection.prepareStatement("DELETE FROM invoice_line WHERE invoice_id=?")) {
            deleteLinesStmt.setInt(1, invoiceId);
            deleteLinesStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Erreur lors de la suppression des lignes de facture", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
    
    
    @Override
    public List<Invoice> getUserInvoices(int id) {
        logger.info("Entering getUserInvoices method with userId: " + id);
        List<Invoice> invoices = new ArrayList<>();

        try {
            String selectUserInvoicesSQL =
                    "SELECT i.id AS invoice_id, i.invoice_number, il.id AS line_id, il.description, il.amount " +
                    "FROM invoice i " +
                    "LEFT JOIN invoice_line il ON i.id = il.invoice_id " +
                    "WHERE i.user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectUserInvoicesSQL)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int invoiceId = resultSet.getInt("invoice_id");
                        String invoiceNumber = resultSet.getString("invoice_number");

                        // Rechercher une facture existante ou en créer une nouvelle
                        Invoice invoice = invoices.stream()
                            .filter(inv -> inv.getId() == invoiceId)
                            .findFirst()
                            .orElse(new Invoice());

                        // Définir les champs communs
                        invoice.setId(invoiceId);
                        invoice.setInvoiceNumber(invoiceNumber);

                        // Initialiser la liste des lignes de facture de la facture si elle est nulle
                        if (invoice.getInvoiceLines() == null) {
                            invoice.setInvoiceLines(new ArrayList<>());
                        }

                        // Ajouter la ligne de facture à la liste des lignes de la facture
                        if (resultSet.getInt("line_id") != 0) {
                            Invoiceline line = new Invoiceline();
                            line.setId(resultSet.getInt("line_id"));
                            line.setDescription(resultSet.getString("description"));
                            line.setAmount(resultSet.getBigDecimal("amount"));
                            invoice.getInvoiceLines().add(line);
                        }

                        // Si la facture n'est pas dans la liste, l'ajouter
                        if (!invoices.contains(invoice)) {
                            invoices.add(invoice);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des factures de l'utilisateur", e);
        }
        logger.info("Exiting getUserInvoices method");
        return invoices;
    }
}


