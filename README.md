Système de Livraison de Colis



Description

Ce projet simule un système de gestion des livraisons de colis. Il utilise JavaFX pour l'interface graphique, et des mécanismes de gestion des threads (avec des sémaphores) pour simuler le processus de livraison. Un seul colis est traité à la fois grâce à l'utilisation d'un sémaphore, et l'interface graphique est mise à jour en temps réel pour afficher le statut de chaque colis.

Fonctionnalités

Ajout de colis : Permet d'ajouter un nouveau colis avec un ID, un destinataire et une adresse.
Simulation de livraison : Lorsqu'un colis est ajouté, il est traité via un thread simulant un délai de livraison.
Suivi en temps réel : Affichage des colis en cours de livraison avec mise à jour de leur statut ("En Transit", "Livré").
Historique : Enregistrement de l'historique des livraisons dans un fichier texte (livraison_histori.txt).
Téléchargement de l'historique : Possibilité de télécharger l'historique des livraisons sous forme de fichier texte.
Prérequis
Avant de commencer, assurez-vous d'avoir installé les éléments suivants :

JDK 8 ou supérieur : Le projet utilise Java pour le backend et JavaFX pour l'interface graphique.
IDE Java : Recommandé pour un développement facile (ex. Eclipse, IntelliJ IDEA, NetBeans).
JavaFX : Bien que JavaFX soit inclus dans les dernières versions du JDK, vous devrez peut-être configurer l'environnement JavaFX si vous utilisez une version plus ancienne de Java.
Installation
Cloner le projet :

bash
Copier le code
git clone https://github.com/votre-utilisateur/systeme-livraison-colis.git
cd systeme-livraison-colis
Configurer JavaFX (si nécessaire) :

Si vous utilisez JDK 11 ou supérieur, vous devrez télécharger et configurer JavaFX séparément. Suivez les instructions sur le site officiel de JavaFX.
Compiler et exécuter :

Ouvrez le projet dans votre IDE préféré.
Assurez-vous que le SDK Java est bien configuré dans votre projet.
Exécutez la classe principale SystemLivraisoncolis pour lancer l'application JavaFX.
Utilisation
Interface
Ajouter un colis : Entrez un ID, un destinataire et une adresse, puis cliquez sur "Ajouter et Livrer". Le colis sera ajouté à la liste et sa livraison sera simulée.
Table des colis : Vous pouvez voir la liste des colis, leur statut et leur temps de livraison dans une table.
Enregistrer l'historique : Cliquez sur "Enregistrer" pour sauvegarder l'historique des livraisons dans un fichier texte.
Afficher l'historique : Cliquez sur "Show Historique" pour ouvrir une nouvelle fenêtre affichant l'historique des livraisons.
Télécharger l'historique : Vous pouvez télécharger l'historique sous forme de fichier texte en cliquant sur "Télécharger" dans l'écran d'historique.
Fichier d'historique
L'historique des livraisons est sauvegardé dans le fichier livraison_histori.txt et contient les informations suivantes pour chaque colis :

Copier le code
ID,Destinateur,Adresse,Statut,Temps de Livraison
Vous pouvez également télécharger l'historique sous forme de fichier texte.

Structure du projet
Le projet est structuré comme suit :

bash
Copier le code
/systeme-livraison-colis
├── src/
│   ├── com/
│   │   ├── mycompany/
│   │   │   ├── systemlivraisoncolis/
│   │   │   │   ├── Colis.java                # Classe représentant un colis
│   │   │   │   ├── ServiceLivraison.java      # Service de gestion des livraisons
│   │   │   │   ├── LivraisonThread.java       # Thread dédié à la gestion des livraisons
│   │   │   │   └── SystemLivraisoncolis.java  # Classe principale pour l'interface graphique
├── livraison_histori.txt                       # Fichier pour stocker l'historique des livraisons
├── README.md                                   # Fichier README
└── .gitignore                                  # Fichier pour ignorer les fichiers non nécessaires au suivi git
Contribuer
Si vous souhaitez contribuer à ce projet, suivez ces étapes :

Forkez le projet.
Créez une branche pour votre fonctionnalité (git checkout -b feature/ma-fonctionnalite).
Commitez vos changements (git commit -m 'Ajout de ma fonctionnalité').
Poussez la branche sur votre fork (git push origin feature/ma-fonctionnalite).
Ouvrez une pull request pour discuter de vos modifications.
Auteurs
Votre Nom – Développeur principal
Collaborateur(s) – Ajoutez des contributeurs si applicable
Licence
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.
