package Medipass;
import Medipass.dossier.Ordonnance;
import Medipass.patient.Patient;
import Medipass.utilisateur.Infirmier;
import Medipass.utilisateur.Medecin;
import Medipass.utilisateur.Pharmacien;
import Medipass.utilisateur.Utilisateur;
import Medipass.admin.Administrateur;

import javax.lang.model.type.NullType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

public class SystemeMedipass {

    private static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<String> historique = new ArrayList<>();
    private static ArrayList<String> prescriptions = new ArrayList<>();
    private static int dernierIdUtilisateur = 0;
    private static final String IDENTIFIANT_ADMIN = "admin";
    private static final String MOT_DE_PASSE_ADMIN = "admin123";
    private boolean disponbilite;
    private static ArrayList<Ordonnance> ordonnances = new ArrayList<>();
    private static int dernierIdOrdonnance = 0;

    // Méthode creer compte pour les pros de sante

    //Medecin
    public void ajouter_medecin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CRÉATION DE COMPTE MÉDIPASS ===");

        // Informations personnelles
        System.out.println("Veuillez saisir votre nom : ");
        String nom = sc.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Le nom est obligatoire : ");
            nom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre prénom : ");
        String prenom = sc.nextLine().trim();
        while (prenom.isEmpty()) {
            System.out.println("Le prénom est obligatoire : ");
            prenom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre email : ");
        String email = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre specialite : ");
        String specialite = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre numéro de téléphone : ");
        String numero_de_telephone = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre adresse : ");
        String adresse = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre date de naissance : ");
        String date_de_naissance = sc.nextLine().trim();

        //  Génération AUTOMATIQUE de l'identifiant
        String id = genererIdMedical(nom, prenom);

        // Mot de passe avec confirmation
        System.out.println("Veuillez saisir votre mot de passe : ");
        String motDePasse = sc.nextLine().trim();

        System.out.println("Veuillez confirmer votre mot de passe : ");
        String confirmation = sc.nextLine().trim();

        while (!motDePasse.equals(confirmation)) {
            System.out.println("Les mots de passe ne correspondent pas !");
            System.out.println("Veuillez saisir votre mot de passe : ");
            motDePasse = sc.nextLine().trim();
            System.out.println("Veuillez confirmer votre mot de passe : ");
            confirmation = sc.nextLine().trim();
        }

        Medecin u = new Medecin(id, nom, prenom, email, numero_de_telephone, date_de_naissance, adresse,specialite, disponbilite, motDePasse);
        utilisateurs.add(u);
        System.out.println(" Compte utilisateur créé avec succès !");
        System.out.println(" Nom : " + u.getPrenom() + " " + u.getNom());
        System.out.println(" Votre identifiant : " + u.getId());
        historique.add(" Compte de medecin cree pour "+ u.getPrenom() + " " + u.getNom()+" identifiant : " + u.getId());
    }

    //creer compte pour infirmier
    public void ajouter_infirmier() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CRÉATION DE COMPTE MÉDIPASS ===");

        // Informations personnelles
        System.out.println("Veuillez saisir votre nom : ");
        String nom = sc.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Le nom est obligatoire : ");
            nom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre prénom : ");
        String prenom = sc.nextLine().trim();
        while (prenom.isEmpty()) {
            System.out.println("Le prénom est obligatoire : ");
            prenom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre email : ");
        String email = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre specialite : ");
        String specialite = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre numéro de téléphone : ");
        String numero_de_telephone = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre adresse : ");
        String adresse = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre date de naissance : ");
        String date_de_naissance = sc.nextLine().trim();

        //  Génération AUTOMATIQUE de l'identifiant
        String id = genererIdMedical(nom, prenom);

        // Mot de passe avec confirmation
        System.out.println("Veuillez saisir votre mot de passe : ");
        String motDePasse = sc.nextLine().trim();

        System.out.println("Veuillez confirmer votre mot de passe : ");
        String confirmation = sc.nextLine().trim();

        while (!motDePasse.equals(confirmation)) {
            System.out.println("Les mots de passe ne correspondent pas !");
            System.out.println("Veuillez saisir votre mot de passe : ");
            motDePasse = sc.nextLine().trim();
            System.out.println("Veuillez confirmer votre mot de passe : ");
            confirmation = sc.nextLine().trim();
        }

        Infirmier u = new Infirmier(id, nom, prenom, email, numero_de_telephone, date_de_naissance, adresse, specialite, disponbilite, motDePasse);
        utilisateurs.add(u);
        System.out.println(" Compte utilisateur créé avec succès !");
        System.out.println(" Nom : " + u.getPrenom() + " " + u.getNom());
        System.out.println(" Votre identifiant : " + u.getId());
        historique.add(" Compte d'infirmier cree pour "+ u.getPrenom() + " " + u.getNom()+" identifiant : " + u.getId());
    }

    //creer compte pour le pharmacien

    public void ajouter_pharmacien() {
        Scanner sc = new Scanner(System.in);
        String specialite = "";

        System.out.println("=== CRÉATION DE COMPTE MÉDIPASS ===");

        // Informations personnelles
        System.out.println("Veuillez saisir votre nom : ");
        String nom = sc.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Le nom est obligatoire : ");
            nom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre prénom : ");
        String prenom = sc.nextLine().trim();
        while (prenom.isEmpty()) {
            System.out.println("Le prénom est obligatoire : ");
            prenom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre email : ");
        String email = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre numéro de téléphone : ");
        String numero_de_telephone = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre adresse : ");
        String adresse = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre date de naissance : ");
        String date_de_naissance = sc.nextLine().trim();

        //  Génération AUTOMATIQUE de l'identifiant
        String id = genererIdMedical(nom, prenom);

        // Mot de passe avec confirmation
        System.out.println("Veuillez saisir votre mot de passe : ");
        String motDePasse = sc.nextLine().trim();

        System.out.println("Veuillez confirmer votre mot de passe : ");
        String confirmation = sc.nextLine().trim();

        while (!motDePasse.equals(confirmation)) {
            System.out.println("Les mots de passe ne correspondent pas !");
            System.out.println("Veuillez saisir votre mot de passe : ");
            motDePasse = sc.nextLine().trim();
            System.out.println("Veuillez confirmer votre mot de passe : ");
            confirmation = sc.nextLine().trim();
        }

        Pharmacien u = new Pharmacien(id, nom, prenom, email, numero_de_telephone, date_de_naissance, adresse, specialite, motDePasse);
        utilisateurs.add(u);
        System.out.println(" Compte utilisateur créé avec succès !");
        System.out.println(" Nom : " + u.getPrenom() + " " + u.getNom());
        System.out.println(" Votre identifiant : " + u.getId());
        historique.add(" Compte utilisateur cree pour "+ u.getPrenom() + " " + u.getNom()+" identifiant : " + u.getId());
    }

        //  MÉTHODE DE GÉNÉRATION D'IDENTIFIANT
        private static String genererIdMedical(String nom, String prenom) {
            dernierIdUtilisateur++;

            // Nettoyer le nom (enlever accents, espaces)
            String nomPropre = nettoyerChaine(nom);
            String prenomPropre = nettoyerChaine(prenom);

            // Prendre les 4 premières lettres du nom (ou moins)
            String partieNom = nomPropre.length() >= 4 ?
                    nomPropre.substring(0, 4).toUpperCase() :
                    nomPropre.toUpperCase();

            // Prendre la première lettre du prénom
            String initialePrenom = prenomPropre.substring(0, 1).toUpperCase();

            // Formater l'ID sur 3 chiffres
            String idFormatte = String.format("%03d", dernierIdUtilisateur);

            return  ("MED_" + partieNom + "_" + initialePrenom + "_" + idFormatte);
        }

        // Méthode pour nettoyer les chaînes (enlever accents, espaces)
        private static String nettoyerChaine(String chaine) {
            return chaine
                    .toLowerCase()
                    .replaceAll("[éèêë]", "e")
                    .replaceAll("[àâä]", "a")
                    .replaceAll("[îï]", "i")
                    .replaceAll("[ôö]", "o")
                    .replaceAll("[ùûü]", "u")
                    .replaceAll("[ç]", "c")
                    .replaceAll("[^a-z]", ""); // Garder seulement les lettres
        }


    //historique
    public static void listageHistorique() {
        System.out.println("=== HISTORIQUE DES ACTIONS ===");

        for (int i = 0; i < historique.size(); i++) {
            System.out.println((i + 1) + ". " + historique.get(i));
        }
    }
    //ajouter patient
    public static void ajouterPatient(){
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CRÉATION DE PATIENT MÉDIPASS ===");

        // Informations personnelles
        System.out.println("Veuillez saisir votre nom : ");
        String nom = sc.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Le nom est obligatoire : ");
            nom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre prénom : ");
        String prenom = sc.nextLine().trim();
        while (prenom.isEmpty()) {
            System.out.println("Le prénom est obligatoire : ");
            prenom = sc.nextLine().trim();
        }

        System.out.println("Veuillez saisir votre email : ");
        String email = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre numéro de téléphone : ");
        String numero_de_telephone = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre adresse : ");
        String adresse = sc.nextLine().trim();

        System.out.println("Veuillez saisir votre date de naissance : ");
        String date_de_naissance = sc.nextLine().trim();

        System.out.println("Veuillez Entrer votre sexe (M pour masculin et F pour feminin : ");
        String sexe = sc.nextLine().trim().toUpperCase();
        do {
            if (!sexe.equals("M") && !sexe.equals("F")) {
                System.out.println("Erreur : vous devez entrer M ou F :");
                sexe = sc.nextLine().trim().toUpperCase();
            }

        } while (!sexe.equals("M") && !sexe.equals("F"));

        //  Génération AUTOMATIQUE de l'identifiant
        String id = genererIdMedical(nom, prenom);

        Patient p = new Patient(id, nom, prenom, email, numero_de_telephone, date_de_naissance, adresse, sexe);
        patients.add(p);
        System.out.println(" Compte utilisateur créé avec succès !");
        System.out.println(" Nom : " + p.getPrenom() + " " + p.getNom());
        System.out.println(" Votre identifiant : " + p.getId());
        historique.add(" Compte patient cree pour "+ p.getPrenom() + " " + p.getNom()+" identifiant : " + p.getId());
    }


        // pour la prescription de medicaments
        public static void prescrireMedicament(Utilisateur utilisateur) {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== PRESCRIPTION MÉDICALE ===");

            // INFORMATION PATIENT
            System.out.print("ID du patient : ");
            String patientId = sc.nextLine().trim();

            // Vérifier que le patient existe
            if (!patientExiste(patientId)) {
                System.out.println("❌ Patient non trouvé");
                return;
            }

            // Trouver le nom du patient
            String patientNom = trouverNomPatient(patientId);
            String medecinNom = utilisateur.getPrenom() + " " + utilisateur.getNom();

            // CRÉATION ORDONNANCE AMÉLIORÉE
            dernierIdOrdonnance++;
            String ordonnanceId = "ORD_" + String.format("%04d", dernierIdOrdonnance);

            // Utiliser le nouveau constructeur
            Ordonnance ordonnance = new Ordonnance(ordonnanceId, utilisateur.getId(), medecinNom, patientId, patientNom);

            // SAISIE DES MÉDICAMENTS AMÉLIORÉE
            boolean continuer = true;
            while (continuer) {
                System.out.println("\n--- Nouveau médicament ---");
                ordonnance.ajouterMedicamentInteractif(); // ✅ Utilisation de la nouvelle méthode

                System.out.print("\nAjouter un autre médicament ? (o/n) : ");
                String reponse = sc.nextLine().trim().toLowerCase();
                continuer = reponse.equals("o") || reponse.equals("oui");
            }

            // INSTRUCTIONS COMPLÉMENTAIRES
            System.out.print("Instructions complémentaires : ");
            ordonnance.setInstructions(sc.nextLine().trim());

            // DURÉE DU TRAITEMENT
            System.out.print("Durée du traitement (en jours) : ");
            ordonnance.setDureeTraitement(sc.nextInt());
            sc.nextLine();

            // SAUVEGARDE
            ordonnances.add(ordonnance);
            historique.add("Ordonnance " + ordonnanceId + " prescrite par " + utilisateur.getNom() + " pour patient " + patientId);

            // CONFIRMATION AVEC AFFICHAGE DÉTAILLÉ
            System.out.println("\n✅ Ordonnance créée avec succès !");
            ordonnance.afficherOrdonnanceDetaillee(); // ✅ Utilisation de la nouvelle méthode
        }

        // ✅ NOUVELLE MÉTHODE POUR TROUVER LE NOM DU PATIENT
        private static String trouverNomPatient(String patientId) {
            for (Patient patient : patients) {
                if (patient.getId().equals(patientId)) {
                    return patient.getPrenom() + " " + patient.getNom();
                }
            }
            for (Utilisateur user : utilisateurs) {
                if (user.getId().equals(patientId)) {
                    return user.getPrenom() + " " + user.getNom();
                }
            }
            return "Patient inconnu";
        }

        // ✅ AMÉLIORATION DE L'AFFICHAGE DES ORDONNANCES
        private static void afficherOrdonnance(Ordonnance ordonnance) {
            // Remplacer l'ancien affichage par le nouveau détaillé
            ordonnance.afficherOrdonnanceDetaillee();
        }

        // ✅ NOUVELLE MÉTHODE POUR GÉRER LE STATUT DES ORDONNANCES
        public static void gererStatutOrdonnance(Utilisateur utilisateur) {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== GESTION DU STATUT DES ORDONNANCES ===");
            System.out.print("ID de l'ordonnance : ");
            String ordId = sc.nextLine();

            Ordonnance ordonnance = trouverOrdonnanceParId(ordId);
            if (ordonnance == null) {
                System.out.println("❌ Ordonnance non trouvée");
                return;
            }

            // Vérifier les permissions
            if (!ordonnance.getMedecinId().equals(utilisateur.getId()) &&
                    !(utilisateur instanceof Medecin)) {
                System.out.println("❌ Vous n'avez pas la permission de modifier cette ordonnance");
                return;
            }

            System.out.println("Statut actuel : " + ordonnance.getStatutFormate());
            System.out.println("Nouveau statut :");
            System.out.println("1. ACTIVE");
            System.out.println("2. TERMINEE");
            System.out.println("3. ANNULEE");
            System.out.print("Choix : ");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1: ordonnance.setStatut("ACTIVE"); break;
                case 2:
                    ordonnance.marquerCommeTerminee();
                    historique.add("Ordonnance " + ordId + " marquée comme terminée par " + utilisateur.getNom());
                    break;
                case 3:
                    ordonnance.annulerOrdonnance();
                    historique.add("Ordonnance " + ordId + " annulée par " + utilisateur.getNom());
                    break;
                default: System.out.println("❌ Choix invalide");
            }
        }

        // ✅ MÉTHODE POUR TROUVER UNE ORDONNANCE PAR ID
        private static Ordonnance trouverOrdonnanceParId(String ordonnanceId) {
            for (Ordonnance ord : ordonnances) {
                if (ord.getId().equals(ordonnanceId)) {
                    return ord;
                }
            }
            return null;
        }

        // ✅ AMÉLIORATION DE LA CONSULTATION DES ORDONNANCES
        public static void consulterOrdonnances(Utilisateur utilisateur) {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== CONSULTATION DES ORDONNANCES ===");
            System.out.println("1. Mes ordonnances (Patient)");
            System.out.println("2. Mes prescriptions (Médecin)");
            System.out.println("3. Toutes les ordonnances (Admin/Pharmacien)");
            System.out.println("4. Gérer le statut d'une ordonnance");
            System.out.print("Choix : ");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    afficherOrdonnancesPatient(utilisateur.getId());
                    break;
                case 2:
                    afficherOrdonnancesMedecin(utilisateur.getId());
                    break;
                case 3:
                    if (utilisateur instanceof Medecin || utilisateur instanceof Pharmacien) {
                        afficherToutesOrdonnances();
                    } else {
                        System.out.println("❌ Accès non autorisé");
                    }
                    break;
                case 4:
                    gererStatutOrdonnance(utilisateur);
                    break;
                default:
                    System.out.println("❌ Choix invalide");
            }
        }

        // ✅ MÉTHODE POUR EXPORTER TOUTES LES ORDONNANCES EN CSV
        public static void exporterOrdonnancesCSV(String nomFichier) {
            try {
                FileWriter writer = new FileWriter(nomFichier + ".csv");

                // En-tête CSV
                writer.write("ID;MedecinID;MedecinNom;PatientID;PatientNom;DatePrescription;DureeTraitement;Instructions;Statut\n");

                int count = 0;
                for (Ordonnance ordonnance : ordonnances) {
                    writer.write(ordonnance.toCSV() + "\n");
                    count++;
                }

                writer.close();
                System.out.println("✅ " + count + " ordonnances exportées vers " + nomFichier + ".csv");
                historique.add("Export CSV des ordonnances: " + count + " ordonnances");

            } catch (Exception e) {
                System.out.println("❌ Erreur lors de l'export: " + e.getMessage());
            }
        }

        // ✅ MÉTHODE POUR IMPORTER DES ORDONNANCES DEPUIS CSV
        public static void importerOrdonnancesCSV(String nomFichier) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(nomFichier + ".csv"));
                String line;
                int count = 0;
                Ordonnance currentOrdonnance = null;

                // Lire l'en-tête
                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    if (!line.contains("MEDICAMENT")) {
                        // Nouvelle ordonnance
                        currentOrdonnance = Ordonnance.fromCSV(line);
                        if (currentOrdonnance != null) {
                            ordonnances.add(currentOrdonnance);
                            count++;
                        }
                    } else if (currentOrdonnance != null) {
                        // Ligne de médicament
                        currentOrdonnance.ajouterMedicamentFromCSV(line);
                    }
                }

                reader.close();
                System.out.println("✅ " + count + " ordonnances importées depuis " + nomFichier + ".csv");
                historique.add("Import CSV des ordonnances: " + count + " ordonnances");

            } catch (FileNotFoundException e) {
                System.out.println("❌ Fichier non trouvé: " + nomFichier + ".csv");
            } catch (Exception e) {
                System.out.println("❌ Erreur lors de l'import: " + e.getMessage());
            }
        }

        // ✅ MÉTHODE POUR EXPORTER LES PATIENTS EN CSV
        public static void exporterPatientsCSV(String nomFichier) {
            try {
                FileWriter writer = new FileWriter(nomFichier + ".csv");

                // En-tête CSV
                writer.write("ID;Nom;Prenom;Email;Telephone;DateNaissance;Adresse;Sexe\n");

                for (Patient patient : patients) {
                    writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                            patient.getId(),
                            patient.getNom(),
                            patient.getPrenom(),
                            patient.getEmail(),
                            patient.getNumero_de_telephone(),
                            patient.getDate_de_naissance(),
                            patient.getAdresse(),
                            patient.getSexe()
                    ));
                }

                writer.close();
                System.out.println("✅ " + patients.size() + " patients exportés vers " + nomFichier + ".csv");
                historique.add("Export CSV des patients: " + patients.size() + " patients");

            } catch (Exception e) {
                System.out.println("❌ Erreur lors de l'export des patients: " + e.getMessage());
            }
        }

        // ✅ MÉTHODE POUR EXPORTER L'HISTORIQUE EN CSV
        public static void exporterHistoriqueCSV(String nomFichier) {
            try {
                FileWriter writer = new FileWriter(nomFichier + ".csv");

                writer.write("Date;Action\n");

                for (String action : historique) {
                    writer.write(new Date() + ";" + action.replace(";", ",") + "\n");
                }

                writer.close();
                System.out.println("✅ Historique exporté vers " + nomFichier + ".csv");

            } catch (Exception e) {
                System.out.println("❌ Erreur lors de l'export de l'historique: " + e.getMessage());
            }
        }

        // ✅ MÉTHODE POUR GÉRER L'IMPORT/EXPORT DANS LE MENU
        public static void menuImportExport(Utilisateur utilisateur) {
            Scanner sc = new Scanner(System.in);
            boolean continuer = true;

            while (continuer) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("           📁 IMPORT/EXPORT CSV");
                System.out.println("=".repeat(50));
                System.out.println("1. Exporter les ordonnances en CSV");
                System.out.println("2. Importer des ordonnances depuis CSV");
                System.out.println("3. Exporter les patients en CSV");
                System.out.println("4. Exporter l'historique en CSV");
                System.out.println("5. Exporter tous les données");
                System.out.println("0. Retour");
                System.out.print("Votre choix : ");

                try {
                    int choix = sc.nextInt();
                    sc.nextLine();

                    switch (choix) {
                        case 1:
                            System.out.print("Nom du fichier (sans extension) : ");
                            String fichierOrdonnances = sc.nextLine();
                            exporterOrdonnancesCSV(fichierOrdonnances);
                            break;
                        case 2:
                            System.out.print("Nom du fichier (sans extension) : ");
                            String fichierImport = sc.nextLine();
                            importerOrdonnancesCSV(fichierImport);
                            break;
                        case 3:
                            System.out.print("Nom du fichier (sans extension) : ");
                            String fichierPatients = sc.nextLine();
                            exporterPatientsCSV(fichierPatients);
                            break;
                        case 4:
                            System.out.print("Nom du fichier (sans extension) : ");
                            String fichierHistorique = sc.nextLine();
                            exporterHistoriqueCSV(fichierHistorique);
                            break;
                        case 5:
                            exporterToutesDonnees();
                            break;
                        case 0:
                            continuer = false;
                            break;
                        default:
                            System.out.println("❌ Choix invalide");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Erreur de saisie");
                    sc.nextLine();
                }
            }
        }

        // ✅ MÉTHODE POUR EXPORTER TOUTES LES DONNÉES
        public static void exporterToutesDonnees() {
            String timestamp = new Date().toString().replace(" ", "_").replace(":", "-");

            exporterOrdonnancesCSV("ordonnances_" + timestamp);
            exporterPatientsCSV("patients_" + timestamp);
            exporterHistoriqueCSV("historique_" + timestamp);

            System.out.println("✅ Toutes les données exportées avec le timestamp: " + timestamp);
        }

        // ✅ MÉTHODE PATIENTEXISTE CORRIGÉE
        private static boolean patientExiste(String patientId) {
            // Vérifier dans la liste des patients
            for (Patient patient : patients) {
                if (patient.getId().equals(patientId)) {
                    return true;
                }
            }
            // Vérifier dans la liste des utilisateurs (au cas où)
            for (Utilisateur user : utilisateurs) {
                if (user.getId().equals(patientId)) {
                    return true;
                }
            }
            return false;
        }

        // ✅ MÉTHODE POUR TROUVER UN PATIENT PAR ID
        public static Patient trouverPatientParId(String patientId) {
            for (Patient patient : patients) {
                if (patient.getId().equals(patientId)) {
                    return patient;
                }
            }
            return null;
        }


        // ✅ AFFICHER ORDONNANCES D'UN PATIENT (COMPLÈTE)
        private static void afficherOrdonnancesPatient(String patientId) {
            System.out.println("\n=== ORDONNANCES DU PATIENT ===");
            boolean trouve = false;

            for (Ordonnance ord : ordonnances) {
                if (ord.getPatientId().equals(patientId)) {
                    ord.afficherOrdonnanceDetaillee();
                    trouve = true;
                }
            }

            if (!trouve) {
                System.out.println("Aucune ordonnance trouvée pour ce patient");
            }
        }

        // ✅ AFFICHER ORDONNANCES D'UN MÉDECIN (COMPLÈTE)
        private static void afficherOrdonnancesMedecin(String medecinId) {
            System.out.println("\n=== MES PRESCRIPTIONS ===");
            boolean trouve = false;

            for (Ordonnance ord : ordonnances) {
                if (ord.getMedecinId().equals(medecinId)) {
                    ord.afficherOrdonnanceDetaillee();
                    trouve = true;
                }
            }

            if (!trouve) {
                System.out.println("Aucune prescription trouvée");
            }
        }

        // ✅ AFFICHER TOUTES LES ORDONNANCES (COMPLÈTE)
        private static void afficherToutesOrdonnances() {
            System.out.println("\n=== TOUTES LES ORDONNANCES ===");
            if (ordonnances.isEmpty()) {
                System.out.println("Aucune ordonnance dans le système");
                return;
            }

            for (Ordonnance ord : ordonnances) {
                ord.afficherOrdonnanceDetaillee();
                System.out.println("─".repeat(40));
            }
        }

    // ✅ STATISTIQUES DES ORDONNANCES
    private static void afficherStatistiquesOrdonnances() {
        System.out.println("\n📊 STATISTIQUES DES ORDONNANCES");
        System.out.println("Total d'ordonnances : " + ordonnances.size());

        int active = 0, terminee = 0, annulee = 0;
        int totalMedicaments = 0;

        for (Ordonnance ord : ordonnances) {
            switch (ord.getStatut()) {
                case "ACTIVE": active++; break;
                case "TERMINEE": terminee++; break;
                case "ANNULEE": annulee++; break;
            }
            totalMedicaments += ord.getMedicaments().size();
        }

        System.out.println("• Actives : " + active);
        System.out.println("• Terminées : " + terminee);
        System.out.println("• Annulées : " + annulee);
        System.out.println("• Total médicaments prescrits : " + totalMedicaments);

        if (!ordonnances.isEmpty()) {
            double moyenneMedicaments = (double) totalMedicaments / ordonnances.size();
            System.out.printf("• Moyenne médicaments/ordonnance : %.1f\n", moyenneMedicaments);
        }
    }



        // ✅ AFFICHER ORDONNANCE SIMPLIFIÉE (pour compatibilité)
        //private static void afficherOrdonnance(Ordonnance ordonnance) {
          //  if (ordonnance != null) {
            //    ordonnance.afficherOrdonnanceDetaillee();
            //}

        // ✅ MÉTHODES POUR OBTENIR LES LISTES D'UTILISATEURS
        public static ArrayList<Utilisateur> getUtilisateurs() {
            return new ArrayList<>(utilisateurs);
        }

        public static ArrayList<Medecin> getMedecins() {
            ArrayList<Medecin> medecins = new ArrayList<>();
            for (Utilisateur user : utilisateurs) {
                if (user instanceof Medecin) {
                    medecins.add((Medecin) user);
                }
            }
            return medecins;
        }

        public static ArrayList<Infirmier> getInfirmiers() {
            ArrayList<Infirmier> infirmiers = new ArrayList<>();
            for (Utilisateur user : utilisateurs) {
                if (user instanceof Infirmier) {
                    infirmiers.add((Infirmier) user);
                }
            }
            return infirmiers;
        }

        public static ArrayList<Pharmacien> getPharmaciens() {
            ArrayList<Pharmacien> pharmaciens = new ArrayList<>();
            for (Utilisateur user : utilisateurs) {
                if (user instanceof Pharmacien) {
                    pharmaciens.add((Pharmacien) user);
                }
            }
            return pharmaciens;
        }

    public static ArrayList<Patient> getPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        for (Utilisateur user : utilisateurs) {
            if (user.estPatient()) {  // ✅ Utiliser la méthode de vérification
                Object patient = new Object();
                patients.add((Patient) patient);
            }
        }
        return patients;
    }

        public static ArrayList<Administrateur> getAdministrateurs() {
            ArrayList<Administrateur> admins = new ArrayList<>();
            for (Utilisateur user : utilisateurs) {
                if (user instanceof Administrateur) {
                    admins.add((Administrateur) user);
                }
            }
            return admins;
        }

        // ✅ MÉTHODE POUR COMPTER LES UTILISATEURS PAR TYPE
        public static HashMap<String, Integer> getStatistiquesUtilisateurs() {
            HashMap<String, Integer> stats = new HashMap<>();
            stats.put("MEDECIN", 0);
            stats.put("INFIRMIER", 0);
            stats.put("PHARMACIEN", 0);
            stats.put("PATIENT", 0);
            stats.put("ADMIN", 0);
            stats.put("TOTAL", utilisateurs.size());

            for (Utilisateur user : utilisateurs) {
                String role = user.getRole();
                stats.put(role, stats.getOrDefault(role, 0) + 1);
            }

            return stats;
        }



}





