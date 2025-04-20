package com.ryuk.bank.DTO;

import java.sql.Timestamp;

public class ClientDTO {
	private String id, nom, prenom, email, hashed_password;
	private Timestamp date_creation;
	
	
	public ClientDTO() {
		super();
	}
	
	public ClientDTO(String id, String nom, String prenom, String email, String hashed_password,
			Timestamp date_creation) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.hashed_password = hashed_password;
		this.date_creation = date_creation;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHashed_password() {
		return hashed_password;
	}
	public void setHashed_password(String hashed_password) {
		this.hashed_password = hashed_password;
	}
	public Timestamp getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Timestamp date_creation) {
		this.date_creation = date_creation;
	}
	
	
	@Override
	public String toString() {
	    return "ClientDTO{" +
	            "id='" + id + '\'' +
	            ", nom='" + nom + '\'' +
	            ", prenom='" + prenom + '\'' +
	            ", email='" + email + '\'' +
	            ", hashed_password='" + hashed_password + '\'' +
	            ", date_creation=" + date_creation +
	            '}';
	}

}
