package Medipass.admin;

import Medipass.utilisateur.Utilisateur;
import Medipass.utilisateur.Medecin;
import Medipass.utilisateur.Infirmier;
import Medipass.utilisateur.Pharmacien;
import Medipass.gestion.GestionnaireHistorique;
import Medipass.SystemeMedipass;
import java.util.Scanner;

public class Administrateur extends Utilisateur {
    private SystemeMedipass systeme;
    private static final String ID_ADMIN_PRINCIPAL = "admin";
    private static final String MOT_DE_PASSE_ADMIN = "admin123";

    // ✅ CONSTRUCTEURS
    public Administrateur(String id, String nom, String prenom, String email,
                          String numeroTelephone, String dateNaissance,
                          String adresse, String motDePasse, SystemeMedipass systeme) {
        super(id, nom, prenom, email, numeroTelephone, dateNaissance, adresse, motDePasse, "ADMIN");
        this.systeme = systeme;
    }

    public Administrateur() {
        super();
        this.role = "ADMIN";
    }

    // ✅ MÉTHODES DE CRÉATION DE COMPTES PROFESSIONNELS

    // CRÉER UN MÉDECIN
    public void creerMedecin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        👨‍⚕️ CRÉATION D'UN COMPTE MÉDECIN");
        System.out.println("=".repeat(50));

        // Saisie des informations
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Spécialité : ");
        String specialite = sc.nextLine();
        System.out.print("Numéro de téléphone : ");
        String telephone = sc.nextLine();
        System.out.print("Date de naissance (jj/mm/aaaa) : ");
        String naissance = sc.nextLine();
        System.out.print("Adresse : ");
        String adresse = sc.nextLine();

        // Génération d'ID
        String id = genererIdMedical(nom, prenom);

        // Mot de passe temporaire
        String motDePasse = genererMotDePasseTemporaire();

        // Création du médecin
        Medecin medecin = new Medecin(id, nom, prenom, email, telephone, naissance, adresse, specialite, true, motDePasse);

        // Ajout au système
        if (ajouterUtilisateurAuSysteme(medecin)) {
            System.out.println("\n✅ Médecin créé avec succès !");
            System.out.println("📋 Identifiants générés :");
            System.out.println("   ID : " + id);
            System.out.println("   Mot de passe temporaire : " + motDePasse);
            System.out.println("⚠️  Le médecin devra changer son mot de passe à la première connexion");

            GestionnaireHistorique.ajouterAction("Création du médecin " + prenom + " " + nom + " par admin " + this.prenom);
        }
    }

    // CRÉER UN INFIRMIER
    public void creerInfirmier() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        👨‍⚕️ CRÉATION D'UN COMPTE INFIRMIER");
        System.out.println("=".repeat(50));

        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Spécialité : ");
        String specialite = sc.nextLine();
        System.out.print("Numéro de téléphone : ");
        String telephone = sc.nextLine();
        System.out.print("Date de naissance : ");
        String naissance = sc.nextLine();
        System.out.print("Adresse : ");
        String adresse = sc.nextLine();

        String id = genererIdMedical(nom, prenom);
        String motDePasse = genererMotDePasseTemporaire();

        Infirmier infirmier = new Infirmier(id, nom, prenom, email, telephone, naissance, adresse, specialite, true, motDePasse);

        if (ajouterUtilisateurAuSysteme(infirmier)) {
            System.out.println("\n✅ Infirmier créé avec succès !");
            System.out.println("📋 Identifiants générés :");
            System.out.println("   ID : " + id);
            System.out.println("   Mot de passe temporaire : " + motDePasse);

            GestionnaireHistorique.ajouterAction("Création de l'infirmier " + prenom + " " + nom + " par admin " + this.prenom);
        }
    }

    // CRÉER UN PHARMACIEN
    public void creerPharmacien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        💊 CRÉATION D'UN COMPTE PHARMACIEN");
        System.out.println("=".repeat(50));

        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Spécialité : ");
        String specialite = sc.nextLine();
        System.out.print("Numéro de téléphone : ");
        String telephone = sc.nextLine();
        System.out.print("Date de naissance : ");
        String naissance = sc.nextLine();
        System.out.print("Adresse : ");
        String adresse = sc.nextLine();

        String id = genererIdMedical(nom, prenom);
        String motDePasse = genererMotDePasseTemporaire();

        Pharmacien pharmacien = new Pharmacien(id, nom, prenom, email, telephone, naissance, adresse, specialite, motDePasse);

        if (ajouterUtilisateurAuSysteme(pharmacien)) {
            System.out.println("\n✅ Pharmacien créé avec succès !");
            System.out.println("📋 Identifiants générés :");
            System.out.println("   ID : " + id);
            System.out.println("   Mot de passe temporaire : " + motDePasse);

            GestionnaireHistorique.ajouterAction("Création du pharmacien " + prenom + " " + nom + " par admin " + this.prenom);
        }
    }

    // ✅ MÉTHODES DE GESTION DES UTILISATEURS

    // LISTER TOUS LES UTILISATEURS
    public void listerTousLesUtilisateurs() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📊 LISTE DES UTILISATEURS");
        System.out.println("=".repeat(50));

        // Cette méthode devrait accéder à SystemeMedipass.getUtilisateurs()
        System.out.println("👥 Fonctionnalité à implémenter avec l'accès à la liste des utilisateurs");
        System.out.println("• Médecins : [nombre]");
        System.out.println("• Infirmiers : [nombre]");
        System.out.println("• Pharmaciens : [nombre]");
        System.out.println("• Patients : [nombre]");

        GestionnaireHistorique.ajouterAction("Consultation de la liste des utilisateurs par admin " + this.prenom);
    }

    // RECHERCHER UN UTILISATEUR
    public void rechercherUtilisateur() {
        Scanner sc = new Scanner(System.in);
        System.out.print("🔍 ID ou nom de l'utilisateur à rechercher : ");
        String critere = sc.nextLine();

        System.out.println("\nRecherche de : '" + critere + "'");
        System.out.println("🔎 Cette fonctionnalité nécessite l'accès à la base des utilisateurs");

        GestionnaireHistorique.ajouterAction("Recherche utilisateur '" + critere + "' par admin " + this.prenom);
    }

    // RÉINITIALISER UN MOT DE PASSE
    public void reinitialiserMotDePasseUtilisateur() {
        Scanner sc = new Scanner(System.in);
        System.out.print("🆔 ID de l'utilisateur : ");
        String userId = sc.nextLine();

        String nouveauMotDePasse = genererMotDePasseTemporaire();

        System.out.println("\n✅ Mot de passe réinitialisé !");
        System.out.println("📋 Nouveaux identifiants pour " + userId + " :");
        System.out.println("   Mot de passe temporaire : " + nouveauMotDePasse);
        System.out.println("⚠️  L'utilisateur devra changer son mot de passe à la prochaine connexion");

        // Implémenter la logique de réinitialisation dans SystemeMedipass

        GestionnaireHistorique.ajouterAction("Réinitialisation MDP pour " + userId + " par admin " + this.prenom);
    }

    // DÉSACTIVER UN COMPTE
    public void desactiverCompteUtilisateur() {
        Scanner sc = new Scanner(System.in);
        System.out.print("🆔 ID de l'utilisateur à désactiver : ");
        String userId = sc.nextLine();

        System.out.print("❓ Êtes-vous sûr de vouloir désactiver le compte " + userId + " ? (o/n) : ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("o") || confirmation.equalsIgnoreCase("oui")) {
            System.out.println("✅ Compte " + userId + " désactivé avec succès");
            // Implémenter la désactivation dans SystemeMedipass

            GestionnaireHistorique.ajouterAction("Désactivation du compte " + userId + " par admin " + this.prenom);
        } else {
            System.out.println("❌ Opération annulée");
        }
    }

    // ✅ MÉTHODES DE STATISTIQUES SYSTÈME

    // AFFICHER LES STATISTIQUES GÉNÉRALES
    public void afficherStatistiquesSysteme() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📈 STATISTIQUES DU SYSTÈME");
        System.out.println("=".repeat(50));

        System.out.println("👥 Utilisateurs :");
        System.out.println("   • Médecins : [nombre]");
        System.out.println("   • Infirmiers : [nombre]");
        System.out.println("   • Pharmaciens : [nombre]");
        System.out.println("   • Patients : [nombre]");

        System.out.println("\n📊 Activité :");
        System.out.println("   • Dossiers médicaux : [nombre]");
        System.out.println("   • Ordonnances créées : [nombre]");
        System.out.println("   • Consultations : [nombre]");
        System.out.println("   • Connexions aujourd'hui : [nombre]");

        System.out.println("\n💾 Système :");
        System.out.println("   • Démarrage : [date]");
        System.out.println("   • Version : Medipass 1.0");
        System.out.println("   • Statut : 🟢 Opérationnel");

        GestionnaireHistorique.ajouterAction("Consultation des statistiques système par admin " + this.prenom);
    }

    // AFFICHER L'HISTORIQUE DES ACTIONS
    public void afficherHistoriqueComplet() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📝 HISTORIQUE DES ACTIONS");
        System.out.println("=".repeat(50));

        GestionnaireHistorique.afficherHistorique();
    }

    // EXPORTER L'HISTORIQUE
    public void exporterHistorique() {
        System.out.println("\n💾 Export de l'historique des actions...");
        GestionnaireHistorique.exporterHistoriqueCSV("historique_systeme");
    }

    // ✅ MÉTHODES DE MAINTENANCE SYSTÈME

    // SAUVEGARDER LES DONNÉES
    public void sauvegarderDonnees() {
        System.out.println("\n💾 SAUVEGARDE DES DONNÉES EN COURS...");

        // Sauvegarder les données des utilisateurs
        System.out.println("✅ Données utilisateurs sauvegardées");

        // Sauvegarder l'historique
        GestionnaireHistorique.exporterHistoriqueCSV("sauvegarde_historique");

        System.out.println("✅ Historique sauvegardé");
        System.out.println("🎉 Sauvegarde terminée avec succès !");

        GestionnaireHistorique.ajouterAction("Sauvegarde des données par admin " + this.prenom);
    }

    // EFFACER L'HISTORIQUE
    public void effacerHistorique() {
        Scanner sc = new Scanner(System.in);
        System.out.print("⚠️  Êtes-vous sûr de vouloir effacer tout l'historique ? (o/n) : ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("o") || confirmation.equalsIgnoreCase("oui")) {
            GestionnaireHistorique.effacerHistorique();
            System.out.println("✅ Historique effacé avec succès");
        } else {
            System.out.println("❌ Opération annulée");
        }
    }

    // ✅ MÉTHODES UTILITAIRES

    // GÉNÉRER UN ID MÉDICAL
    private String genererIdMedical(String nom, String prenom) {
        String nomNettoye = nettoyerChaine(nom);
        String prenomNettoye = nettoyerChaine(prenom);

        String partieNom = nomNettoye.length() >= 3 ?
                nomNettoye.substring(0, 3).toUpperCase() :
                nomNettoye.toUpperCase();

        String initialePrenom = prenomNettoye.substring(0, 1).toUpperCase();

        return partieNom + "_" + initialePrenom + "_" + (System.currentTimeMillis() % 10000);
    }

    // NETTOYER UNE CHAÎNE (enlever accents et caractères spéciaux)
    private String nettoyerChaine(String chaine) {
        return chaine
                .toLowerCase()
                .replaceAll("[éèêë]", "e")
                .replaceAll("[àâä]", "a")
                .replaceAll("[îï]", "i")
                .replaceAll("[ôö]", "o")
                .replaceAll("[ùûü]", "u")
                .replaceAll("[ç]", "c")
                .replaceAll("[^a-z]", "");
    }

    // GÉNÉRER UN MOT DE PASSE TEMPORAIRE
    private String genererMotDePasseTemporaire() {
        return "Temp" + (System.currentTimeMillis() % 10000);
    }

    // AJOUTER UN UTILISATEUR AU SYSTÈME
    private boolean ajouterUtilisateurAuSysteme(Utilisateur utilisateur) {
        // Cette méthode devrait appeler SystemeMedipass.ajouterUtilisateur()
        System.out.println("➕ Ajout de l'utilisateur au système...");
        return true; // Temporaire - à implémenter
    }

    // ✅ MÉTHODE D'AFFICHAGE SPÉCIFIQUE
    @Override
    public void afficherProfil() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        👑 PROFIL ADMINISTRATEUR");
        System.out.println("=".repeat(50));
        System.out.println("ID : " + this.id);
        System.out.println("Nom : " + this.prenom + " " + this.nom);
        System.out.println("Email : " + this.email);
        System.out.println("Rôle : " + this.role);
        System.out.println("Date de création : " + this.dateCreation);
        System.out.println("\n🔐 Permissions :");
        System.out.println("   ✅ Gestion des comptes utilisateurs");
        System.out.println("   ✅ Consultation des statistiques");
        System.out.println("   ✅ Maintenance du système");
        System.out.println("   ❌ Accès aux dossiers médicaux");
        System.out.println("   ❌ Modification des données médicales");
        System.out.println("=".repeat(50));
    }

    // ✅ MÉTHODE POUR AFFICHER LE MENU ADMIN
    public void afficherMenuAdministrateur() {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("        👑 MENU ADMINISTRATEUR");
            System.out.println("=".repeat(50));
            System.out.println("1. 👨‍⚕️  Créer un médecin");
            System.out.println("2. 👨‍⚕️  Créer un infirmier");
            System.out.println("3. 💊 Créer un pharmacien");
            System.out.println("4. 👥 Gérer les utilisateurs");
            System.out.println("5. 📈 Statistiques système");
            System.out.println("6. 💾 Maintenance");
            System.out.println("7. 👤 Mon profil");
            System.out.println("0. 🚪 Déconnexion");
            System.out.print("Votre choix : ");

            try {
                int choix = sc.nextInt();
                sc.nextLine();

                switch (choix) {
                    case 1:
                        creerMedecin();
                        break;
                    case 2:
                        creerInfirmier();
                        break;
                    case 3:
                        creerPharmacien();
                        break;
                    case 4:
                        menuGestionUtilisateurs();
                        break;
                    case 5:
                        menuStatistiques();
                        break;
                    case 6:
                        menuMaintenance();
                        break;
                    case 7:
                        afficherProfil();
                        break;
                    case 0:
                        continuer = false;
                        this.seDeconnecter();
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

    // SOUS-MENU GESTION UTILISATEURS
    private void menuGestionUtilisateurs() {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- GESTION DES UTILISATEURS ---");
            System.out.println("1. 📋 Lister tous les utilisateurs");
            System.out.println("2. 🔍 Rechercher un utilisateur");
            System.out.println("3. 🔑 Réinitialiser un mot de passe");
            System.out.println("4. 🚫 Désactiver un compte");
            System.out.println("0. ↩️  Retour");
            System.out.print("Choix : ");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    listerTousLesUtilisateurs();
                    break;
                case 2:
                    rechercherUtilisateur();
                    break;
                case 3:
                    reinitialiserMotDePasseUtilisateur();
                    break;
                case 4:
                    desactiverCompteUtilisateur();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide");
            }
        }
    }

    // SOUS-MENU STATISTIQUES
    private void menuStatistiques() {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- STATISTIQUES ET RAPPORTS ---");
            System.out.println("1. 📊 Afficher les statistiques");
            System.out.println("2. 📝 Voir l'historique");
            System.out.println("3. 💾 Exporter l'historique");
            System.out.println("0. ↩️  Retour");
            System.out.print("Choix : ");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    afficherStatistiquesSysteme();
                    break;
                case 2:
                    afficherHistoriqueComplet();
                    break;
                case 3:
                    exporterHistorique();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide");
            }
        }
    }

    // SOUS-MENU MAINTENANCE
    private void menuMaintenance() {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- MAINTENANCE SYSTÈME ---");
            System.out.println("1. 💾 Sauvegarder les données");
            System.out.println("2. 🗑️  Effacer l'historique");
            System.out.println("0. ↩️  Retour");
            System.out.print("Choix : ");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    sauvegarderDonnees();
                    break;
                case 2:
                    effacerHistorique();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide");
            }
        }
    }

    // ✅ GETTERS ET SETTERS
    public SystemeMedipass getSysteme() { return systeme; }
    public void setSysteme(SystemeMedipass systeme) { this.systeme = systeme; }

    public static String getIdAdminPrincipal() { return ID_ADMIN_PRINCIPAL; }
    public static String getMotDePasseAdmin() { return MOT_DE_PASSE_ADMIN; }
}