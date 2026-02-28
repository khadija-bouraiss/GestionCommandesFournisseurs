-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 28 fév. 2026 à 16:09
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `commandefournisseur`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande_achat`
--

CREATE TABLE `commande_achat` (
  `id_commande` int(11) NOT NULL,
  `id_fournisseur` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `dateCommande` date NOT NULL,
  `quantite` int(11) NOT NULL CHECK (`quantite` > 0),
  `statut` enum('En cours','Livrée') NOT NULL DEFAULT 'En cours'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande_achat`
--

INSERT INTO `commande_achat` (`id_commande`, `id_fournisseur`, `id_produit`, `dateCommande`, `quantite`, `statut`) VALUES
(1, 1, 1, '2025-12-15', 5, 'Livrée'),
(2, 2, 2, '2026-02-20', 3, 'Livrée'),
(3, 3, 3, '2026-01-03', 10, 'Livrée'),
(4, 1, 4, '2026-01-15', 2, 'Livrée'),
(5, 2, 5, '2026-01-27', 20, 'Livrée'),
(6, 1, 1, '2026-02-21', 5, 'Livrée'),
(7, 1, 1, '2026-02-02', 7, 'En cours'),
(9, 2, 3, '2026-02-04', 8, 'Livrée'),
(10, 1, 1, '2026-02-05', 9, 'En cours'),
(11, 7, 2, '2026-02-04', 4, 'Livrée'),
(13, 1, 2, '2026-02-12', 3, 'Livrée'),
(14, 1, 5, '2026-01-08', 4, 'Livrée'),
(15, 1, 6, '2026-01-06', 8, 'Livrée'),
(17, 15, 10, '2026-04-03', 5, 'En cours'),
(18, 4, 4, '2026-03-10', 2, 'En cours'),
(19, 2, 11, '2026-04-21', 6, 'En cours'),
(20, 7, 6, '2026-02-04', 3, 'En cours');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id_fournisseur`, `nom`, `ville`, `telephone`) VALUES
(1, 'Dell Maroc', 'Casablanca', '0622334455'),
(2, 'HP Maghreb', 'Rabat', '0537112233'),
(3, 'Lenovo Africa', 'Marrakech', '0739887766'),
(4, 'Samsung Maroc', 'Tanger', '0612345678'),
(7, 'Apple Maghreb', 'Safi', '0698765437'),
(15, 'Canon Maroc', 'Fès', '0661234567'),
(16, 'Lenovo Tech', 'Agadir', '0677889909');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id_produit` int(11) NOT NULL,
  `libelle` varchar(100) NOT NULL,
  `categorie` varchar(50) NOT NULL,
  `prixAchat` double NOT NULL CHECK (`prixAchat` > 0),
  `stock` int(11) NOT NULL DEFAULT 0 CHECK (`stock` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `libelle`, `categorie`, `prixAchat`, `stock`) VALUES
(1, 'Laptop Dell i5', 'Informatique', 8500, 15),
(2, 'Imprimante HP', 'Bureautique', 1200, 15),
(3, 'Clavier Lenovo', 'Accessoires', 250, 20),
(4, 'Ecran 24 pouces', 'Informatique', 2200, 8),
(5, 'Souris optique', 'Accessoires', 76, 74),
(6, 'Tablette Samsung', 'Informatique', 3200, 17),
(10, 'Imprimante Canon', 'Bureautique', 1800, 12),
(11, 'Switch Réseau', 'Réseau', 2800, 5),
(12, 'Disque Dur 1To', 'Stockage', 600, 23),
(13, 'Ecran Dell 27', 'Informatique', 3270, 9);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `passwordHash` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `login`, `passwordHash`, `email`) VALUES
(1, 'admin', '$2a$12$T2rlr6Z6ggeviLLg2SbwpeIBAIxFKBtJnhZWW6Qm75wbtY7XshDSq', 'k.bouraiss9327@uca.ac.ma');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commande_achat`
--
ALTER TABLE `commande_achat`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `id_fournisseur` (`id_fournisseur`),
  ADD KEY `id_produit` (`id_produit`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`),
  ADD UNIQUE KEY `libelle` (`libelle`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commande_achat`
--
ALTER TABLE `commande_achat`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande_achat`
--
ALTER TABLE `commande_achat`
  ADD CONSTRAINT `commande_achat_ibfk_1` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`),
  ADD CONSTRAINT `commande_achat_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
