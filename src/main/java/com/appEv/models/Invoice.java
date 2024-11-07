package com.appEv.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "invoice")
public class Invoice {      //facture

	    private int id;
	    private String invoiceNumber;
	    private List<Invoiceline> invoiceLines;
	    private int userId;
	    private User user;
	    public Invoice() {
	    	
	    }
	    
	    public Invoice(int id, String invoiceNumber,User user ,List<Invoiceline> invoiceLines) {
			super();
			this.id = id;
			this.user=user;
			this.invoiceNumber = invoiceNumber;
			this.invoiceLines = invoiceLines;
		}
	    
	    public Invoice( String invoiceNumber,User user ,List<Invoiceline> invoiceLines) {
			super();
			this.user=user;
			this.invoiceNumber = invoiceNumber;
			this.invoiceLines = invoiceLines;
		}
	    
	    public Invoice(String invoiceNumber, int userId, List<Invoiceline> invoiceLines) {
	        this.invoiceNumber = invoiceNumber;
	        this.userId = userId;
	        this.invoiceLines = invoiceLines;
	    }
	    
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getInvoiceNumber() {
			return invoiceNumber;
		}
		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}
		public List<Invoiceline> getInvoiceLines() {
			return invoiceLines;
		}
		public void setInvoiceLines(List<Invoiceline> invoiceLines) {
			this.invoiceLines = invoiceLines;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		
		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Invoice [id=");
			builder.append(id);
			builder.append(", invoiceNumber=");
			builder.append(invoiceNumber);
			builder.append(", invoiceLines=");
			builder.append(invoiceLines);
			builder.append(", user=");
			builder.append(user);
			builder.append("]");
			return builder.toString();
		}


	

	  
		
}
