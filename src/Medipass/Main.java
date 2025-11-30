package Medipass;

import Medipass.admin.Administrateur;
import Medipass.utilisateur.Utilisateur;
import Medipass.utilisateur.Medecin;
import Medipass.utilisateur.Infirmier;
import Medipass.utilisateur.Pharmacien;
import Medipass.patient.Patient;
import Medipass.gestion.GestionnaireDossiers;
import Medipass.gestion.GestionnaireHistorique;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SystemeMedipass systeme = new SystemeMedipass();

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      🏥 BIENVENUE DANS MEDIPASS 🏥");
        System.out.println("    Système de Gestion Médicale");
        System.out.println("==========================================");

        // Charger les données sauvegardées
        GestionnaireDossiers.chargerEtat();

        boolean quitter = false;

        // debut de modification
        List<String> categorie = Arrays.asList("Administrateur", "Utilisateur");
        System.out.println("\nQuel serait votre role dans ce systeme ?\n [ Administrateur || Utilisateur ]");
        String role;
        int essais = 0;
        do {
            if (essais > 0) 
                System.out.println("*** Recommencez");
            role = scanner.nextLine();
            essais++;
        } while (!categorie.contains(role));
        System.out.println("==========================================\n");
        // //
        int choix = 0;
        String userResponse;
        
        System.out.println("Est-ce votre votre premiere utilisation de notre systeme? [ Y / N ]");
        do {
            userResponse = scanner.nextLine();
        } while ( !userResponse.equals("Y") && !userResponse.equals("N") );
        //
        if ( userResponse.equals("Y") ) {
            if ( role.equals(categorie.getFirst()) ) { // Administrateur
                System.out.println("\nAlors veuillez \n ");
                System.out.println("1. 👤 Creer compte adminstrateur");
                System.out.println("0. ❌ Quitter");
                while (!quitter) {
                    choix = saisirEntier("Votre choix : ");
                    switch (choix) {
                        case 1:
                            creerCompteAdmintrateur();
                            quitter = true;
                            System.out.println("==========================================\n");
                            break;
                        case 0:
                            quitter = true;
                            System.out.println("==========================================\n");
                            System.out.println("Dommage!\n System Shutin' down! \n");
                            GestionnaireDossiers.sauvegarderEtat(); // Sauvegarder avant de quitter
                            break;
                        default:
                            System.out.println("❌ Choix invalide. Veuillez réessayer.");
                    }
                }
            } else { // Utilisateur
                System.out.println("\nComme il en est ainsi \n veuillez attendre l'intervention d'un Administrateur \n ");
                quitter = true;
                System.out.println("_________ATTENTE_DE_L'INTERVENTION_D'UN_ADMIN_________");
                System.out.println("==========================================\n");
            }
            // handlin' boolean quitter
            if (choix == 1) // ou autres valueurs [ sauf 0 -> quitter ]
                quitter = false;
        } // else suite [ Connection ]

        while (!quitter) {
            if (role.equals(categorie.getFirst())) { // Administrateur
                afficherMenuPrincipalAdmin();
                choix = saisirEntier("Votre choix : ");
                
                switch (choix) {
                    case 1:
                        connexionAdmin();
                        break;
                    case 2:
                        afficherStatistiquesRapides();
                        break;
                    case 0:
                        quitter = true;
                        System.out.println("👋 Merci d'avoir utilisé Medipass !");
                        // Sauvegarder avant de quitter
                        GestionnaireDossiers.sauvegarderEtat();
                        break;
                    default:
                        System.out.println("❌ Choix invalide. Veuillez réessayer.");
                }
            } else {
                afficherMenuPrincipalUser();
                choix = saisirEntier("Votre choix : ");
                switch (choix) {
                    case 1:
                        connexionMedecin();
                        break;
                    case 2:
                        connexionInfirmier();
                        break;
                    case 3:
                        connexionPharmacien();
                        break;/*
                    case 4:
                        afficherStatistiquesRapides();
                        break;*/
                    case 0:
                        quitter = true;
                        System.out.println("👋 Merci d'avoir utilisé Medipass !");
                        // Sauvegarder avant de quitter
                        GestionnaireDossiers.sauvegarderEtat();
                        break;
                    default:
                        System.out.println("❌ Choix invalide. Veuillez réessayer.");
                }
            }
        }
        scanner.close();
    }

    // modif : afficherMenuPrincipal ---> Admin & User
    private static void afficherMenuPrincipalAdmin() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            🏠 MENU PRINCIPAL ADMIN");
        System.out.println("=".repeat(50));
        System.out.println("1. 👑 Connexion Administrateur");
        System.out.println("2. 📊 Statistiques rapides");
        System.out.println("0. 🚪 Quitter");
        System.out.println("=".repeat(50));
    }

    private static void afficherMenuPrincipalUser() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            🏠 MENU PRINCIPAL USERS");
        System.out.println("=".repeat(50));
        System.out.println("1. 👨‍⚕️  Connexion Médecin");
        System.out.println("2. 👨‍⚕️  Connexion Infirmier");
        System.out.println("3. 💊 Connexion Pharmacien");
        //System.out.println("4. 📊 Statistiques rapides");
        System.out.println("0. 🚪 Quitter");
        System.out.println("=".repeat(50));
    }

    // 🔐 CONNEXION ADMINISTRATEUR
    private static void connexionAdmin() {
        System.out.println("\n=== CONNEXION ADMINISTRATEUR ===");

        if (Administrateur.connecterAdmin()) {
            // Créer une instance d'admin temporaire pour le menu
            Administrateur admin = new Administrateur();
            admin.afficherMenuAdministrateur();
        } else {
            System.out.println("❌ Échec de la connexion administrateur.");
        }
    }

    // 👨‍⚕️ CONNEXION MÉDECIN
    private static void connexionMedecin() {
        System.out.println("\n=== CONNEXION MÉDECIN ===");

        if (Utilisateur.seConnecter()) {
            // Simulation d'un médecin connecté
            Medecin medecin = new Medecin();
            menuMedecin(medecin);
        } else {
            System.out.println("❌ Échec de la connexion médecin.");
        }
    }

    // 👨‍⚕️ CONNEXION INFIRMIER
    private static void connexionInfirmier() {
        System.out.println("\n=== CONNEXION INFIRMIER ===");

        if (Utilisateur.seConnecter()) {
            Infirmier infirmier = new Infirmier();
            menuInfirmier(infirmier);
        } else {
            System.out.println("❌ Échec de la connexion infirmier.");
        }
    }

    // 💊 CONNEXION PHARMACIEN
    private static void connexionPharmacien() {
        System.out.println("\n=== CONNEXION PHARMACIEN ===");

        if (Utilisateur.seConnecter()) {
            Pharmacien pharmacien = new Pharmacien();
            menuPharmacien(pharmacien);
        } else {
            System.out.println("❌ Échec de la connexion pharmacien.");
        }
    }

    // 👤 CREATION COMPTE ADMINTRATEUR
    private static void creerCompteAdmintrateur() {
        System.out.println("\n=== CREATION COMPTE ADMINTRATEUR ===");
        Administrateur.creer_admin();
    }

    // 📝 CRÉATION DE COMPTE UTILISATEUR
    private static void creerCompteUtilisateur() {
        System.out.println("\n=== CRÉATION DE COMPTE ===");
        //System.out.println("1. 👑 Compte Administrateur");
        System.out.println("1. 👨‍⚕️  Compte Médecin");
        System.out.println("2. 👨‍⚕️  Compte Infirmier");
        System.out.println("3. 💊 Compte Pharmacien");
        //System.out.println("5. 👤 Compte Patient");
        System.out.println("0. ↩️  Retour");

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1:
                systeme.ajouter_medecin();
                break;
            case 2:
                systeme.ajouter_infirmier();
                break;
            case 3:
                systeme.ajouter_pharmacien();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Choix invalide.");
        }
    }

    // 🏥 MENU MÉDECIN
    private static void menuMedecin(Medecin medecin) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("        👨‍⚕️ MENU MÉDECIN");
            System.out.println("=".repeat(50));
            System.out.println("1. 📁 Créer dossier médical");
            System.out.println("2. 📋 Consulter dossier patient");
            System.out.println("3. 💊 Prescrire ordonnance");
            System.out.println("4. 📝 Creer consultations");
            System.out.println("5. 👤 Créer patient");
            System.out.println("6. 📊 Mes statistiques");
            System.out.println("7. 👤 Mon profil");
            System.out.println("8. 📁 Import/Export");
            System.out.println("0. 🚪 Déconnexion");

            int choix = saisirEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    medecin.creerDossierMedical();
                    break;
                case 2:
                    medecin.consulterDossierPatient();
                    break;
                case 3:
                    medecin.prescrireOrdonnance();
                    break;
                case 4:
                    Medecin.creerConsultationStatic();
                    break;
                case 5:
                    medecin.creerPatient();
                    break;
                case 6:
                    medecin.afficherStatistiques();
                    break;
                case 7:
                    medecin.afficherProfil();
                    break;
                case 8:
                    SystemeMedipass.menuImportExport(medecin);
                    break;
                case 0:
                    continuer = false;
                    medecin.seDeconnecter();
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }

    // 🏥 MENU INFIRMIER
    private static void menuInfirmier(Infirmier infirmier) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("        👨‍⚕️ MENU INFIRMIER");
            System.out.println("=".repeat(50));
            System.out.println("1. 📋 Consulter dossier patient");
            System.out.println("2. 📝 Ajouter observations");
            System.out.println("3. 👤 Mon profil");
            System.out.println("0. 🚪 Déconnexion");

            int choix = saisirEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    infirmier.consulterDossierPatient();
                    break;
                case 2:
                    infirmier.ajouterObservations();
                    break;
                case 3:
                    infirmier.afficherProfil();
                    break;
                case 0:
                    continuer = false;
                    infirmier.seDeconnecter();
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }

    // 💊 MENU PHARMACIEN
    private static void menuPharmacien(Pharmacien pharmacien) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("        💊 MENU PHARMACIEN");
            System.out.println("=".repeat(50));
            System.out.println("1. 📋 Vérifier ordonnance");
            System.out.println("2. 📁 Consulter dossier patient");
            System.out.println("3. 👤 Mon profil");
            System.out.println("4. 📁 Import/Export");
            System.out.println("0. 🚪 Déconnexion");

            int choix = saisirEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    pharmacien.verifierOrdonnance();
                    break;
                case 2:
                    pharmacien.consulterOrdonnancesPatient();
                    break;
                case 3:
                    pharmacien.afficherProfil();
                    break;
                case 4:
                    SystemeMedipass.menuImportExport(pharmacien);
                    break;
                case 0:
                    continuer = false;
                    pharmacien.seDeconnecter();
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }


    // 📊 STATISTIQUES RAPIDES
    private static void afficherStatistiquesRapides() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📊 STATISTIQUES RAPIDES");
        System.out.println("=".repeat(50));

        // Statistiques des dossiers
        GestionnaireDossiers.afficherStatistiquesGenerales();

        // Statistiques des ordonnances
        SystemeMedipass systeme = new SystemeMedipass();

        System.out.println("\n📈 ACTIVITÉ RÉCENTE :");
        GestionnaireDossiers.afficherDossiersPlusActifs();

        System.out.println("\n📝 DERNIÈRES ACTIONS :");
        GestionnaireHistorique.afficherHistorique();
    }

    // 🛠️ MÉTHODE UTILITAIRE POUR LA SAISIE
    private static int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Vider le buffer
            }
        }
    }

    // Méthode pour vider le buffer du scanner
    private static void viderBuffer() {
        scanner.nextLine();
    }
}
