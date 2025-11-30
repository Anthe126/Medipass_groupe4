📋 MANUEL D'UTILISATION - MEDIPASS

🏥 Système de Gestion Médicale

---

📖 SOMMAIRE

1. Présentation
2. Première Utilisation
3. Rôles et Permissions
4. Fonctionnalités
5. Guide Pas à Pas
6. Mode Démonstration
7. Export des Données
8. Dépannage

---

🎯 PRÉSENTATION

Medipass est un système complet de gestion médicale développé en Java qui permet :

· ✅ Gestion centralisée des dossiers patients
· ✅ Suivi des consultations et ordonnances
· ✅ Gestion des antécédents médicaux
· ✅ Administration des comptes utilisateurs
· ✅ Statistiques et rapports

---

🚀 PREMIÈRE UTILISATION

Création du compte Administrateur

1. Compiler le projet 
2. Exécuter la classe Main.java
3. Choisir "Administrateur"
4. Sélectionner "Première utilisation - O"
5. Choisir "Créer un compte administrateur"
6. Remplir les informations :
   · Nom et Prénom
   · Email
   · Téléphone
   · Adresse
   · Date de naissance

📝 Identifiants par défaut :
L'admin a des identifaints par defaut qu'il faut utiliser pour la connexion de l'admin

· Identifiant : admin
· Mot de passe : admin123

---

👥 RÔLES ET PERMISSIONS

👑 ADMINISTRATEUR

· ✅ Création des comptes utilisateurs
· ✅ Gestion des droits d'accès
· ✅ Consultation des statistiques
· ✅ Export des données
· ❌ Accès aux dossiers médicaux

👨‍⚕️ MÉDECIN

· ✅ Création de dossiers médicaux
· ✅ Consultation complète des dossiers
· ✅ Prescription d'ordonnances
· ✅ Gestion des consultations
· ✅ Création de patients

👨‍⚕️ INFIRMIER

· ✅ Consultation des dossiers
· ✅ Ajout d'observations
· ❌ Prescription de médicaments

💊 PHARMACIEN

· ✅ Vérification des ordonnances
· ✅ Consultation des dossiers patients
· ❌ Modification des dossiers

---

🛠️ FONCTIONNALITÉS

Gestion des Patients

· Création et modification de profils patients
· Dossiers médicaux complets
· Historique médical détaillé

Dossiers Médicaux

· Antécédents : Allergies, maladies chroniques, chirurgies
· Consultations : Motif, diagnostic, traitement
· Ordonnances : Médicaments, posologie, durée
· Allergies : Liste complète des allergies

Gestion des Consultations

· Création de nouvelles consultations
· Saisie des observations médicales
· Diagnostic et traitement
· Liaison avec les ordonnances

Ordonnances

· Prescription détaillée des médicaments
· Posologie et instructions
· Durée du traitement
· Statut (Active/Terminée/Annulée)

Statistiques

· Nombre de patients par catégorie
· Activité récente du système
· Historique des actions
· Export en format CSV

---

📝 GUIDE PAS À PAS

Connexion au système

1. Exécuter la classe Main.java
2. Choisir le rôle : Administrateur ou Utilisateur
3. Se connecter avec identifiant/mot de passe

Créer un patient

1. Se connecter en tant que Médecin
2. Menu → "Créer patient"
3. Remplir les informations :
   · Nom, Prénom
   · Email, Téléphone
   · Adresse, Date de naissance
   · Sexe

Créer un dossier médical

1. Menu Médecin → "Créer dossier médical"
2. Saisir l'ID du patient
3. Le système crée automatiquement le dossier

Ajouter des antécédents

1. Consulter le dossier patient
2. Section "Antécédents"
3. "Ajouter un antécédent"
4. Choisir le type :
   · Allergie
   · Maladie chronique
   · Chirurgie
   · Antécédent familial

Prescrire une ordonnance

1. Menu Médecin → "Prescrire ordonnance"
2. Saisir l'ID du patient
3. Ajouter les médicaments :
   · Nom du médicament
   · Posologie
   · Dosage
   · Quantité
   · Forme
4. Définir la durée du traitement

Créer une consultation

1. Menu Médecin → "Créer consultation"
2. Sélectionner le patient
3. Saisir :
   · Motif de la consultation
   · Observations
   · Diagnostic
   · Traitement recommandé

---

🧪 MODE DÉMONSTRATION

Accès au mode démo

· Menu principal → Option 3 "Mode Démonstration"
· Menu Admin → Option 4 "Mode Démonstration"
· Menu Utilisateur → Option 4 "Mode Démonstration"

Données de test créées

· 👑 1 Administrateur
· 👨‍⚕️ 2 Médecins (Cardiologie, Pédiatrie)
· 👨‍⚕️ 1 Infirmier
· 💊 1 Pharmacien
· 👤 3 Patients avec dossiers complets
· 🩺 8 Antécédents médicaux
· 💊 3 Ordonnances avec médicaments
· 🏥 3 Consultations

Utilisation de la démo

1. Lancer le mode démonstration
2. Explorer les données créées
3. Tester toutes les fonctionnalités
4. Utiliser les IDs de test :
   · Patients : PAT_DUBO_A_001, PAT_MART_P_002, PAT_LEFE_J_003
   · Médecins : MED_DUPO_J_001, MED_BERT_M_002

---

📊 EXPORT DES DONNÉES

Formats supportés

· CSV : Compatible Excel/LibreOffice
· Format texte : Lecture simple

Données exportables

· Liste complète des patients
· Ordonnances et prescriptions
· Historique des consultations
· Statistiques système
· Journal d'activité

Procédure d'export

1. Menu → "Import/Export"
2. Choisir le type de données
3. Sélectionner le format
4. Nommer le fichier
5. Confirmer l'export

---

🔧 DÉPANNAGE

Problèmes courants

❌ "Dossier non trouvé"

· Vérifier que le patient existe
· Créer d'abord le patient puis le dossier
· Utiliser l'ID correct du patient

❌ "Patient non trouvé"

· Vérifier l'orthographe de l'ID
· Utiliser le mode démo pour tester
· Créer le patient manuellement

❌ Erreur de connexion

· Vérifier identifiant/mot de passe
· Utiliser admin / admin123 pour admin

❌ Problème d'export CSV

· Vérifier les permissions d'écriture
· Fermer Excel si le fichier est ouvert
· Utiliser un nom de fichier simple


Pour toute assistance technique :

1. Consulter ce manuel
2. Utiliser le mode démonstration


Ce manuel et README couvrent tous les aspectsde notre système !

Merci d'utiliser notre système !!!!

Au revoir !!
