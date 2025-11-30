package Medipass;

import Medipass.admin.Administrateur;
import Medipass.utilisateur.Utilisateur;
import Medipass.utilisateur.Medecin;
import Medipass.utilisateur.Infirmier;
import Medipass.utilisateur.Pharmacien;
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

        boolean applicationActive = true;

        while (applicationActive) {
            // ÉTAPE 1 : Choix du rôle
            String role = choisirRole();
            if (role == null) {
                applicationActive = false;
                continue;
            }

            // ÉTAPE 2 : Première utilisation
            if (estPremiereUtilisation()) {
                if (role.equals("Administrateur")) {
                    gererPremiereUtilisationAdmin();
                } else {
                    attenteInterventionAdmin();
                }
                continue; // Retour au choix du rôle après première utilisation
            }

            // ÉTAPE 3 : Menu principal selon le rôle
            applicationActive = gererMenuPrincipal(role);
        }

        // Fermeture propre
        System.out.println("👋 Merci d'avoir utilisé Medipass !");
        GestionnaireDossiers.sauvegarderEtat();
        scanner.close();
    }

    // ==================== MÉTHODES MODULAIRES ====================

    /**
     * Étape 1 : Choix du rôle (Administrateur ou Utilisateur)
     */
    private static String choisirRole() {
        List<String> roles = Arrays.asList("Administrateur", "Utilisateur");

        System.out.println("\n" + "=".repeat(50));
        System.out.println("        CHOIX DU RÔLE");
        System.out.println("=".repeat(50));
        System.out.println("Voulez-vous utiliser le système en tant que :");
        System.out.println("1. 👑 Administrateur");
        System.out.println("2. 👤 Utilisateur");
        System.out.println("3. 🧪 Mode Démonstration");
        System.out.println("0. ❌ Quitter l'application");
        System.out.println("=".repeat(50));

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1: return "Administrateur";
            case 2: return "Utilisateur";
            case 3:
                lancerModeDemonstration();
                return choisirRole(); // Retour au choix après démo
            case 0: return null; // Quitter
            default:
                System.out.println("❌ Choix invalide. Veuillez réessayer.");
                return choisirRole(); // Rappel récursif
        }
    }

    /**
     * Lance le mode démonstration
     */
    private static void lancerModeDemonstration() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🧪 MODE DÉMONSTRATION");
        System.out.println("=".repeat(50));
        System.out.println("Ce mode va :");
        System.out.println("✅ Créer des utilisateurs de test");
        System.out.println("✅ Générer des dossiers médicaux complets");
        System.out.println("✅ Remplir avec des données réalistes");
        System.out.println("✅ Vous permettre de tester toutes les fonctionnalités");

        System.out.print("\nVoulez-vous lancer la démonstration ? [O/N] : ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("O")) {
            Demonstration.lancerDemonstration();
            System.out.println("\n🎯 Démonstration terminée. Retour au menu principal...");
            System.out.println("Appuyez sur Entrée pour continuer...");
            scanner.nextLine();
        } else {
            System.out.println("❌ Démonstration annulée.");
        }
    }

    /**
     * Étape 2 : Vérification première utilisation
     */
    private static boolean estPremiereUtilisation() {
        System.out.print("\nEst-ce votre première utilisation du système ? [O/N] : ");
        String reponse = scanner.nextLine().trim().toUpperCase();

        while (!reponse.equals("O") && !reponse.equals("N")) {
            System.out.print("❌ Réponse invalide. Veuillez saisir O ou N : ");
            reponse = scanner.nextLine().trim().toUpperCase();
        }

        return reponse.equals("O");
    }

    /**
     * Gestion première utilisation Admin
     */
    private static void gererPremiereUtilisationAdmin() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        PREMIÈRE UTILISATION - ADMIN");
        System.out.println("=".repeat(50));
        System.out.println("1. 👤 Créer un compte administrateur");
        System.out.println("2. 🧪 Lancer le mode démonstration");
        System.out.println("0. ↩️  Retour au choix du rôle");

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1:
                creerCompteAdministrateur();
                break;
            case 2:
                lancerModeDemonstration();
                break;
            case 0:
                // Retour simple au choix du rôle
                break;
            default:
                System.out.println("❌ Choix invalide.");
        }
    }

    /**
     * Message d'attente pour utilisateur sans compte
     */
    private static void attenteInterventionAdmin() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        ATTENTE REQUISE");
        System.out.println("=".repeat(50));
        System.out.println("⚠️  Vous ne pouvez pas créer de compte utilisateur.");
        System.out.println("Veuillez contacter un administrateur pour créer votre compte.");
        System.out.println("=".repeat(50));
        System.out.println("Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    /**
     * Étape 3 : Menu principal selon le rôle
     */
    private static boolean gererMenuPrincipal(String role) {
        boolean dansMenuPrincipal = true;

        while (dansMenuPrincipal) {
            if (role.equals("Administrateur")) {
                dansMenuPrincipal = afficherMenuAdmin();
            } else {
                dansMenuPrincipal = afficherMenuUtilisateur();
            }
        }

        return true; // Continuer l'application (retour au choix du rôle)
    }

    /**
     * Menu principal Administrateur
     */
    private static boolean afficherMenuAdmin() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🏠 MENU ADMINISTRATEUR");
        System.out.println("=".repeat(50));
        System.out.println("1. 👑 Connexion Administrateur");
        System.out.println("2. 📊 Statistiques système");
        System.out.println("3. 📝 Créer comptes utilisateurs");
        System.out.println("4. 🧪 Mode Démonstration");
        System.out.println("0. ↩️  Retour au choix du rôle");
        System.out.println("=".repeat(50));

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1:
                connexionAdmin();
                break;
            case 2:
                afficherStatistiquesRapides();
                break;
            case 3:
                creerCompteUtilisateur();
                break;
            case 4:
                lancerModeDemonstration();
                break;
            case 0:
                return false; // Quitter ce menu
            default:
                System.out.println("❌ Choix invalide.");
        }
        return true; // Rester dans ce menu
    }

    /**
     * Menu principal Utilisateur
     */
    private static boolean afficherMenuUtilisateur() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🏠 MENU UTILISATEUR");
        System.out.println("=".repeat(50));
        System.out.println("1. 👨‍⚕️  Connexion Médecin");
        System.out.println("2. 👨‍⚕️  Connexion Infirmier");
        System.out.println("3. 💊 Connexion Pharmacien");
        System.out.println("4. 🧪 Mode Démonstration");
        System.out.println("0. ↩️  Retour au choix du rôle");
        System.out.println("=".repeat(50));

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1:
                connexionMedecin();
                break;
            case 2:
                connexionInfirmier();
                break;
            case 3:
                connexionPharmacien();
                break;
            case 4:
                lancerModeDemonstration();
                break;
            case 0:
                return false; // Quitter ce menu
            default:
                System.out.println("❌ Choix invalide.");
        }
        return true; // Rester dans ce menu
    }

    // ==================== MÉTHODES DE CONNEXION ====================

    private static void connexionAdmin() {
        System.out.println("\n=== CONNEXION ADMINISTRATEUR ===");
        if (Administrateur.connecterAdmin()) {
            Administrateur admin = new Administrateur();
            admin.afficherMenuAdministrateur();
        }
    }

    private static void connexionMedecin() {
        System.out.println("\n=== CONNEXION MÉDECIN ===");
        if (Utilisateur.seConnecter()) {
            Medecin medecin = new Medecin();
            menuMedecin(medecin);
        }
    }

    private static void connexionInfirmier() {
        System.out.println("\n=== CONNEXION INFIRMIER ===");
        if (Utilisateur.seConnecter()) {
            Infirmier infirmier = new Infirmier();
            menuInfirmier(infirmier);
        }
    }

    private static void connexionPharmacien() {
        System.out.println("\n=== CONNEXION PHARMACIEN ===");
        if (Utilisateur.seConnecter()) {
            Pharmacien pharmacien = new Pharmacien();
            menuPharmacien(pharmacien);
        }
    }

    // ==================== MÉTHODES DE CRÉATION ====================

    private static void creerCompteAdministrateur() {
        System.out.println("\n=== CRÉATION COMPTE ADMINISTRATEUR ===");
        Administrateur.creer_admin();
    }

    private static void creerCompteUtilisateur() {
        System.out.println("\n=== CRÉATION DE COMPTE UTILISATEUR ===");
        System.out.println("1. 👨‍⚕️  Compte Médecin");
        System.out.println("2. 👨‍⚕️  Compte Infirmier");
        System.out.println("3. 💊 Compte Pharmacien");
        System.out.println("0. ↩️  Retour");

        int choix = saisirEntier("Votre choix : ");

        switch (choix) {
            case 1: systeme.ajouter_medecin(); break;
            case 2: systeme.ajouter_infirmier(); break;
            case 3: systeme.ajouter_pharmacien(); break;
            case 0: return;
            default: System.out.println("❌ Choix invalide.");
        }
    }

    // ==================== MENUS SPÉCIALISÉS (inchangés) ====================

    private static void menuMedecin(Medecin medecin) {
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("        👨‍⚕️ MENU MÉDECIN");
            System.out.println("=".repeat(50));
            System.out.println("1. 📁 Créer dossier médical");
            System.out.println("2. 📋 Consulter dossier patient");
            System.out.println("3. 💊 Prescrire ordonnance");
            System.out.println("4. 📝 Créer consultations");
            System.out.println("5. 👤 Créer patient");
            System.out.println("6. 📊 Mes statistiques");
            System.out.println("7. 👤 Mon profil");
            System.out.println("8. 📁 Import/Export");
            System.out.println("0. 🚪 Déconnexion");

            switch (saisirEntier("Votre choix : ")) {
                case 1: medecin.creerDossierMedical(); break;
                case 2: medecin.consulterDossierPatient(); break;
                case 3: medecin.prescrireOrdonnance(); break;
                case 4: medecin.creerConsultationStatic(); break;
                case 5: medecin.creerPatient(); break;
                case 6: medecin.afficherStatistiques(); break;
                case 7: medecin.afficherProfil(); break;
                case 8: SystemeMedipass.menuImportExport(medecin); break;
                case 0: continuer = false; medecin.seDeconnecter(); break;
                default: System.out.println("❌ Choix invalide.");
            }
        }
    }

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

            switch (saisirEntier("Votre choix : ")) {
                case 1: infirmier.consulterDossierPatient(); break;
                case 2: infirmier.ajouterObservations(); break;
                case 3: infirmier.afficherProfil(); break;
                case 0: continuer = false; infirmier.seDeconnecter(); break;
                default: System.out.println("❌ Choix invalide.");
            }
        }
    }

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

            switch (saisirEntier("Votre choix : ")) {
                case 1: pharmacien.verifierOrdonnance(); break;
                case 2: pharmacien.consulterOrdonnancesPatient(); break;
                case 3: pharmacien.afficherProfil(); break;
                case 4: SystemeMedipass.menuImportExport(pharmacien); break;
                case 0: continuer = false; pharmacien.seDeconnecter(); break;
                default: System.out.println("❌ Choix invalide.");
            }
        }
    }

    // ==================== MÉTHODES UTILITAIRES ====================

    private static void afficherStatistiquesRapides() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📊 STATISTIQUES RAPIDES");
        System.out.println("=".repeat(50));
        GestionnaireDossiers.afficherStatistiquesGenerales();
        System.out.println("\n📈 ACTIVITÉ RÉCENTE :");
        GestionnaireDossiers.afficherDossiersPlusActifs();
        System.out.println("\n📝 DERNIÈRES ACTIONS :");
        GestionnaireHistorique.afficherHistorique();
    }

    private static int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                int choix = scanner.nextInt();
                scanner.nextLine(); // Vider le buffer
                return choix;
            } catch (Exception e) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Vider le buffer
            }
        }
    }
}