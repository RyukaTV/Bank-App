package com.ryuk.bank.DTO;

import java.sql.Timestamp;

public class CompteBancaireDTO {
	
	private String id, numeroCompte, clientId, typeCompte, status;
	private double solde;
	private Timestamp dateOuverture;
	
	
	public CompteBancaireDTO() {
		super();
	}

	public CompteBancaireDTO(String id, String numeroCompte, String clientId, String typeCompte, String status,
			double solde, Timestamp dateOuverture) {
		super();
		this.id = id;
		this.numeroCompte = numeroCompte;
		this.clientId = clientId;
		this.typeCompte = typeCompte;
		this.status = status;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Timestamp getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Timestamp dateOuverture) {
		this.dateOuverture = dateOuverture;
	}	
	
}
