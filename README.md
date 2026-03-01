# 🏢 Gestion des Commandes Fournisseurs

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

## 👩‍💻 Auteur
**BOURAISS KHADIJA**  
Faculté des Sciences et Techniques - Marrakech

## 📋 Description
Application de bureau développée en Java Swing pour la gestion des commandes fournisseurs.
Elle permet de gérer les fournisseurs, les produits et les commandes d'achat avec une interface graphique moderne et intuitive.

## 📌 Contexte
Dans le cadre de la gestion des achats d'une entreprise,
le suivi des commandes fournisseurs est une tâche critique
qui nécessite une organisation rigoureuse. La gestion manuelle
de ces opérations engendre des erreurs, des pertes de données
et un manque de visibilité sur les stocks et les dépenses.

## ❓ Problématique
Comment développer une application desktop permettant de
centraliser et automatiser la gestion des commandes fournisseurs,
tout en assurant la sécurité des accès, le suivi en temps réel
des stocks et une analyse statistique des achats ?


## ✨ Fonctionnalités

- 🔐 **Authentification sécurisée** avec hashage BCrypt
- 📦 **Gestion des Fournisseurs** - CRUD complet
- 🛒 **Gestion des Produits** - CRUD complet
- 📋 **Gestion des Commandes** - CRUD + validation livraison
- 📊 **Mise à jour automatique du stock** lors de la livraison
- 🔍 **Filtrage des commandes** par fournisseur, statut et période
- 📈 **Statistiques graphiques** avec JFreeChart
- 📧 **Récupération de mot de passe** par email avec JavaMail
- 💿 **Installateur Windows** avec Inno Setup

## 🏗️ Architecture

<img width="1336" height="733" alt="Screenshot 2026-02-28 165223" src="https://github.com/user-attachments/assets/94a03f04-9d41-4816-9787-52a64a1d7c7e" />



## 🛠️ Technologies utilisées

| Technologie | Version | Utilisation |
|---|---|---|
| Java | 8+ | Langage principal |
| Java Swing | - | Interface graphique |
| MySQL | 8.0 | Base de données |
| JDBC | - | Connexion base de données |
| BCrypt | 0.4 | Hashage des mots de passe |
| JFreeChart | 1.0.19 | Graphiques statistiques |
| JavaMail | 1.6.2 | Envoi d'emails |
| JCalendar | 1.4 | Sélecteur de dates |
| Inno Setup | 6.7 | Installateur Windows |

## 🗄️ Base de données

<img width="1622" height="421" alt="Screenshot 2026-02-28 165549" src="https://github.com/user-attachments/assets/b4678da2-4685-49e9-a116-02810f732a07" />


## 🚀 Installation

### Prérequis
- Java JDK 8+
- XAMPP (MySQL + Apache)

### Étapes
1. Cloner le repository
```bash
git clone https://github.com/khadija-bouraiss/GestionCommandesFournisseurs.git
```

2. Importer la base de données
```
Ouvrir phpMyAdmin → Importer → docs/commandefournisseur.sql
```

3. Lancer XAMPP → démarrer Apache et MySQL

4. Lancer l'application
```
Double cliquer sur dist/GestionCommandes_Setup.exe
```

### Identifiants par défaut
```
Login    : admin
Password : palcKzT2
```

## 📊 Diagrammes UML
Les diagrammes UML sont disponibles dans le dossier `docs/` :
- Diagramme de classes


<img width="1087" height="752" alt="Screenshot 2026-02-28 165940" src="https://github.com/user-attachments/assets/f74ecb0f-aec3-4e9c-92bc-a44bd087d20f" />


  
- Diagramme des cas d'utilisation

<img width="1225" height="852" alt="Screenshot 2026-02-28 170018" src="https://github.com/user-attachments/assets/3f8b9d16-32e2-469e-82df-ddad207b23df" />



## 📁 Structure du projet

<img width="834" height="526" alt="Screenshot 2026-02-28 172730" src="https://github.com/user-attachments/assets/8e952f51-64ae-47ce-8f59-50def6ceef28" />
<img width="839" height="742" alt="Screenshot 2026-02-28 172749" src="https://github.com/user-attachments/assets/7be2168d-e1a0-4b88-b0a2-c8826ca278d8" />
<img width="837" height="886" alt="Screenshot 2026-02-28 172814" src="https://github.com/user-attachments/assets/c5cfa15b-766c-45e2-aeeb-11d3341805ba" />

## 🔐 Sécurité - Hashage BCrypt des mots de passe
Les mots de passe sont stockés de manière sécurisée dans la base de données 
grâce à l'algorithme de hashage BCrypt, garantissant qu'aucun mot de passe 
n'est stocké en clair.

<img width="1703" height="648" alt="Screenshot 2026-02-27 232128" src="https://github.com/user-attachments/assets/88f26ef8-8209-42b9-94b9-a690511dbf7d" />

## 🗑️ Désinstallation de l'application

https://github.com/user-attachments/assets/71518d89-1896-4c8e-bee1-8844dc86b586

## 💿 Installation de l'application

https://github.com/user-attachments/assets/4e47297f-d705-4977-864d-4363a308402d

## 🔑 Authentification & Sécurité

https://github.com/user-attachments/assets/dcc72b80-57d6-4fa1-b1df-01b510329247










