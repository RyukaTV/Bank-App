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

CREATE TABLE compte_bancaire (
    id VARCHAR(36),
    numero_compte VARCHAR(30) UNIQUE NOT NULL,
    client_id VARCHAR(36) REFERENCES client(id) ON DELETE CASCADE,
    type_compte VARCHAR(50),
    solde DECIMAL(15,2) DEFAULT 0.00,
    date_ouverture TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'actif',
    PRIMARY KEY(id)
);

CREATE TABLE transaction (
    id VARCHAR(36),
    compte_source_id VARCHAR(36) REFERENCES compte_bancaire(id),
    compte_dest_id VARCHAR(36) REFERENCES compte_bancaire(id),
    montant DECIMAL(15,2) NOT NULL,
    date_transaction TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type_transaction VARCHAR(50),
    description TEXT,
    PRIMARY KEY(id)
);


INSERT INTO Client (id, nom, prenom, hashed_password, email) VALUES
("a3d6d621-16b9-494c-9123-be6ec700a542", "nom", "prenom", SHA2("mdp", 256), "email@service.com"),
("z6d2d178-95j4-851p-7561-fe0ag481z382", "user", "user", SHA2("user", 256), "user@email-service.com");

INSERT INTO compte_bancaire (id, numero_compte, client_id, type_compte, solde, `status`) VALUES
(UUID(), 'ACC-001-2024', "a3d6d621-16b9-494c-9123-be6ec700a542", 'courant', 1500.00, 'actif'),
(UUID(), 'ACC-002-2024', "a3d6d621-16b9-494c-9123-be6ec700a542", 'épargne', 3200.50, 'actif'),
(UUID(), 'ACC-003-2024', 'a3d6d621-16b9-494c-9123-be6ec700a542', 'courant', 760.30, 'actif'),
(UUID(), 'ACC-004-2024', 'z6d2d178-95j4-851p-7561-fe0ag481z382', 'épargne', 0.00, 'inactif'),
(UUID(), 'ACC-005-2024', 'z6d2d178-95j4-851p-7561-fe0ag481z382', 'courant', 18.50, 'actif');