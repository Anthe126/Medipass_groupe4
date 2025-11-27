
package Medipass.utilisateur;

import Medipass.dossier.Ordonnance;
import Medipass.gestion.GestionnaireDossiers;
import Medipass.dossier.DossierMedical;
import Medipass.SystemeMedipass;
import java.util.Scanner;

public class Medecin extends ProDeSante {
    private int anneesExperience;

    // ✅ CONSTRUCTEURS
    public Medecin(String id, String nom, String prenom, String email,
                   String numeroTelephone, String dateNaissance,
                   String adresse, String specialite, boolean disponibilite, String motDePasse) {
        super(id, nom, prenom, email, numeroTelephone, dateNaissance, adresse, specialite, motDePasse, "MEDECIN");
        this.disponibilite = disponibilite;
    }

    public Medecin() {
        super();
        this.role = "MEDECIN";
    }

    // ✅ MÉTHODES SPÉCIFIQUES AU MÉDECIN
    @Override
    public void afficherMenuSpecifique() {
        System.out.println("\n=== MENU MÉDECIN ===");
        System.out.println("1. Créer un dossier médical");
        System.out.println("2. Consulter un dossier patient");
        System.out.println("3. Prescrire une ordonnance");
        System.out.println("4. Créer une consultation");
        System.out.println("5. Gérer mes disponibilités");
        System.out.println("6. Mes statistiques");
    }

    public void creerDossierMedical() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== CRÉATION DE DOSSIER MÉDICAL ===");
        System.out.print("ID du patient: ");
        String patientId = sc.nextLine();

        // Cette méthode devrait être dans GestionnaireDossiers
        System.out.println("📁 Création du dossier pour le patient: " + patientId);
        // Implémentation à compléter avec GestionnaireDossiers
    }

    public void consulterDossierPatient() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== CONSULTATION DOSSIER ===");
        System.out.print("ID du patient: ");
        String patientId = sc.nextLine();

        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.afficherDossier();
        } else {
            System.out.println("❌ Dossier non trouvé");
        }
    }

    public void prescrireOrdonnance() {
        SystemeMedipass.prescrireMedicament(this);
        //DossierMedical.ajouterOrdonnance(Ordonnance ordonnance);
    }

    public void mesOrdonnances() {
        System.out.println("\n=== MES PRESCRIPTIONS ===");
        // Implémentation à compléter
    }

    public void afficherStatistiques() {
        System.out.println("\n=== MES STATISTIQUES ===");
        System.out.println("Médecin: " + this.prenom + " " + this.nom);
        System.out.println("Spécialité: " + this.specialite);
        System.out.println("Disponible: " + (this.disponibilite ? "Oui" : "Non"));
        // Ajouter plus de statistiques
    }

    public  void creerPatient(){
        SystemeMedipass.ajouterPatient();
    }

    // ✅ GETTERS ET SETTERS
    public int getAnneesExperience() { return anneesExperience; }
    public void setAnneesExperience(int anneesExperience) { this.anneesExperience = anneesExperience; }
}



