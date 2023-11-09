CREATE DATABASE IF NOT EXISTS E_commerce;
USE e_commerce;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- CREATION TABLES :

CREATE TABLE IF NOT EXISTS Utilisateur (
	id_utilisateur int AUTO_INCREMENT UNIQUE PRIMARY KEY,
	nom varchar(20) NOT NULL,
	prenom varchar(20) NOT NULL,
	mail varchar(30) NOT NULL UNIQUE,
	motDePasse varchar(20) NOT NULL,
	TypeDeCompte enum('Admin', 'Moderateur', 'Client') NOT NULL
);

CREATE TABLE IF NOT EXISTS Commande (
	id_commande int AUTO_INCREMENT UNIQUE PRIMARY KEY,
	prix decimal(10,2) NOT NULL,
	status varchar(50) NOT NULL,
    id_client int,
    FOREIGN KEY (id_client) REFERENCES Client(id_client)
);

CREATE TABLE IF NOT EXISTS `Client` (
	id_client int UNIQUE PRIMARY KEY,
	compteBancaireNum varchar(19) NOT NULL,
	compteBancaireSolde decimal(10,2),
    points int,
    FOREIGN KEY (id_client) REFERENCES Utilisateur(id_utilisateur)
);

CREATE TABLE IF NOT EXISTS `Moderateur` (
	id_moderateur int UNIQUE PRIMARY KEY,
	droits varchar(5) NOT NULL,
    FOREIGN KEY (id_moderateur) REFERENCES Utilisateur(id_utilisateur)
);

CREATE TABLE IF NOT EXISTS Produit (
	id_produit int AUTO_INCREMENT UNIQUE PRIMARY KEY,
	nom varchar(50) NOT NULL,
	prix decimal(10,2) NOT NULL,
	description varchar(500),
	stock int CHECK (stock >= 0)
);


CREATE TABLE IF NOT EXISTS Commande_Produit (
    id_commandeProd int AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_commande INT,
    id_produit INT,
    quantite INT,
    FOREIGN KEY (id_commande) REFERENCES Commande(id_commande),
    FOREIGN KEY (id_produit) REFERENCES Produit(id_produit)
);



-- Ajout des données 
INSERT IGNORE INTO Utilisateur (nom, prenom, mail, motDePasse, typeDeCompte) VALUES
	('Belbouab', 'Samy', 'Ymasuu@gmail.com', '1234', 'Admin'),
	('Anderson', 'Warren', 'Mugiwarren@gmail.com', '1234', 'Moderateur'),
	('Pinto', 'Ethan', 'Hanabi@gmail.com', '1234', 'Client'),
	('NASCIMENTO ARDILES', 'Renato', 'RDNATOS@gmail.com', '1234', 'Client');

INSERT IGNORE INTO Produit (nom, prix, description, stock) VALUES
	('Table', 79.99, 'test', 230),
	('Chaise', 24.99, 'test', 400),
	('ventilateur', 81.63, 'test', 40),
	('tasse', 9.99, 'test', 53),
	('ordinateur', 1199.99, 'test', 10),
	('ballon de foot', 14.99, 'test', 60),
	('Lampe de bureau', 30, 'test', 75);

INSERT IGNORE INTO Client (id_client, compteBancaireNum, compteBancaireSolde, points)
	SELECT id_utilisateur, '0000 0000 0000 0000', 0, 0
	FROM Utilisateur
	WHERE TypeDeCompte = 'Client';
    
INSERT IGNORE INTO Moderateur (id_moderateur, droits)
	SELECT id_utilisateur, '101'
	FROM Utilisateur
	WHERE TypeDeCompte = 'Moderateur';
