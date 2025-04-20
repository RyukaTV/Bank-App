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
);

INSERT INTO Client (id, nom, prenom, hashed_password, email) value
("a3d6d621-16b9-494c-9123-be6ec700a542", "nom", "prenom", SHA2("mdp", 256), "email@service.com");