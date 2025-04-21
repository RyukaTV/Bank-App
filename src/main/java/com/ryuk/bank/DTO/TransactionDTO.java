package com.ryuk.bank.DTO;

import java.sql.Timestamp;

public class TransactionDTO {
	
	private String id, compteSourceId, compteDestId, typeTransaction, description;
	private double montant;
	private Timestamp dateTransaction;
	
	
	public TransactionDTO() {
		super();
	}

	public TransactionDTO(String id, String compteSourceId, String compteDestId, String typeTransaction,
			String description, double montant, Timestamp dateTransaction) {
		super();
		this.id = id;
		this.compteSourceId = compteSourceId;
		this.compteDestId = compteDestId;
		this.typeTransaction = typeTransaction;
		this.description = description;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompteSourceId() {
		return compteSourceId;
	}

	public void setCompteSourceId(String compteSourceId) {
		this.compteSourceId = compteSourceId;
	}

	public String getCompteDestId() {
		return compteDestId;
	}

	public void setCompteDestId(String compteDestId) {
		this.compteDestId = compteDestId;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Timestamp getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Timestamp dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	
}
