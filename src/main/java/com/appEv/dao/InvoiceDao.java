package com.appEv.dao;

import java.util.List;

import com.appEv.models.Invoice;
import com.appEv.models.User;

public interface InvoiceDao {

	boolean addInvoice(Invoice invoice);
    boolean updateInvoice(Invoice invoice);
    boolean deleteInvoice(int id);
    Invoice getInvoiceById(int id);
    List<Invoice> getAllInvoices();
    List<Invoice> getUserInvoices(int id);	
	
}
