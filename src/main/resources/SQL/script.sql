DROP DATABASE IF EXISTS bank_database;
CREATE DATABASE bank_database;
USE bank_database;

CREATE TABLE Client(
	id VARCHAR(36),
	nom VARCHAR(100),
	prenom VARCHAR(100),
	hashed_password VARCHAR(255),
	email VARCHAR(255),
	date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
)