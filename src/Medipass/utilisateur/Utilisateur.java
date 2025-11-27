package Medipass.utilisateur;

import Medipass.gestion.GestionnaireHistorique;
import java.util.Scanner;
import java.util.*;

public class Utilisateur {
    // ✅ ATTRIBUTS AMÉLIORÉS
    protected String id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String numeroTelephone;
    protected String dateNaissance;
    protected String adresse;
    protected String motDePasse;
    protected String role; // "PATIENT", "MEDECIN", "INFIRMIER", "PHARMACIEN", "ADMIN"
    protected boolean estConnecte;
    protected Date dateCreation;

    // ✅ CONSTRUCTEURS
    public Utilisateur(String id, String nom, String prenom, String email,
                       String numeroTelephone, String dateNaissance,
                       String adresse, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.role = role;
        this.estConnecte = false;
        this.dateCreation = new Date();
    }

    public Utilisateur() {
        this.dateCreation = new Date();
        this.estConnecte = false;
    }

    // ✅ MÉTHODES DE CONNEXION/DÉCONNEXION
    public boolean seConnecter(String motDePasseTente) {
        if (this.motDePasse.equals(motDePasseTente)) {
            this.estConnecte = true;
            GestionnaireHistorique.ajouterAction("Connexion de " + this.prenom + " " + this.nom + " (" + this.role + ")");
            System.out.println("✅ Connexion réussie ! Bienvenue " + this.prenom);
            return true;
        } else {
            System.out.println("❌ Mot de passe incorrect");
            return false;
        }
    }

    public void seDeconnecter() {
        this.estConnecte = false;
        GestionnaireHistorique.ajouterAction("Déconnexion de " + this.prenom + " " + this.nom);
        System.out.println("👋 Au revoir " + this.prenom);
    }

    // ✅ MÉTHODES DE VALIDATION
    public boolean estMedecin() {
        return "MEDECIN".equals(this.role);
    }

    public boolean estPatient() {
        return "PATIENT".equals(this.role);
    }

    public boolean estInfirmier() {
        return "INFIRMIER".equals(this.role);
    }

    public boolean estPharmacien() {
        return "PHARMACIEN".equals(this.role);
    }

    public boolean estAdmin() {
        return "ADMIN".equals(this.role);
    }

    // ✅ MÉTHODES DE PERMISSION
    public boolean peutPrescrireMedicaments() {
        return estMedecin();
    }

    public boolean peutConsulterDossiers() {
        return estMedecin() || estInfirmier() || estPharmacien();
    }

    public boolean peutModifierDossiers() {
        return estMedecin();
    }

    public boolean peutGererUtilisateurs() {
        return estAdmin();
    }

    // ✅ MÉTHODE POUR METTRE À JOUR LE PROFIL
    public void mettreAJourProfil() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== MODIFICATION DU PROFIL ===");
        System.out.println("Laissez vide pour ne pas modifier");

        System.out.print("Nouvel email (" + this.email + ") : ");
        String nouvelEmail = sc.nextLine();
        if (!nouvelEmail.isEmpty()) this.email = nouvelEmail;

        System.out.print("Nouveau téléphone (" + this.numeroTelephone + ") : ");
        String nouveauTel = sc.nextLine();
        if (!nouveauTel.isEmpty()) this.numeroTelephone = nouveauTel;

        System.out.print("Nouvelle adresse (" + this.adresse + ") : ");
        String nouvelleAdresse = sc.nextLine();
        if (!nouvelleAdresse.isEmpty()) this.adresse = nouvelleAdresse;

        System.out.print("Nouveau mot de passe : ");
        String nouveauMdp = sc.nextLine();
        if (!nouveauMdp.isEmpty()) {
            System.out.print("Confirmer le nouveau mot de passe : ");
            String confirmation = sc.nextLine();
            if (nouveauMdp.equals(confirmation)) {
                this.motDePasse = nouveauMdp;
                System.out.println("✅ Mot de passe modifié");
            } else {
                System.out.println("❌ Les mots de passe ne correspondent pas");
            }
        }

        GestionnaireHistorique.ajouterAction("Profil modifié par " + this.prenom + " " + this.nom);
        System.out.println("✅ Profil mis à jour !");
    }

    // ✅ MÉTHODE D'AFFICHAGE
    public void afficherProfil() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           👤 PROFIL UTILISATEUR");
        System.out.println("=".repeat(40));
        System.out.println("ID: " + this.id);
        System.out.println("Nom: " + this.nom + " " + this.prenom);
        System.out.println("Email: " + this.email);
        System.out.println("Téléphone: " + this.numeroTelephone);
        System.out.println("Date de naissance: " + this.dateNaissance);
        System.out.println("Adresse: " + this.adresse);
        System.out.println("Rôle: " + this.role);
        System.out.println("Date de création: " + this.dateCreation);
        System.out.println("Statut: " + (this.estConnecte ? "🟢 Connecté" : "🔴 Déconnecté"));
        System.out.println("=".repeat(40));
    }

    // ✅ GETTERS ET SETTERS
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }

    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isEstConnecte() { return estConnecte; }
    public Date getDateCreation() { return dateCreation; }

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + " (" + this.role + ")";
    }
}