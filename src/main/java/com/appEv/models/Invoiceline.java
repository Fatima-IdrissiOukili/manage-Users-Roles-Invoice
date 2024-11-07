package com.appEv.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_line")
public class Invoiceline {

	private int id;
    private String description;
    private BigDecimal amount;
    
    public Invoiceline() {
    	
    }
    
	public Invoiceline(int id, String description, BigDecimal amount) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
	}

	 public Invoiceline(String description, BigDecimal amount) {
	        this.description = description;
	        this.amount = amount;
	    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoiceline [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

	
    
    
}
