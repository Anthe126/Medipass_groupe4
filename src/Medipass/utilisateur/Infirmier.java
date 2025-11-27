package Medipass.utilisateur;

import Medipass.gestion.GestionnaireDossiers;
import Medipass.dossier.DossierMedical;
import java.util.Scanner;

public class Infirmier extends ProDeSante {

    public Infirmier(String id, String nom, String prenom, String email,
                     String numeroTelephone, String dateNaissance,
                     String adresse, String specialite, boolean disponibilite, String motDePasse) {
        super(id, nom, prenom, email, numeroTelephone, dateNaissance, adresse, specialite, motDePasse, "INFIRMIER");
        this.disponibilite = disponibilite;
    }

    public Infirmier() {
        super();
        this.role = "INFIRMIER";
    }

    @Override
    public void afficherMenuSpecifique() {
        System.out.println("\n=== MENU INFIRMIER ===");
        System.out.println("1. Consulter un dossier patient");
        System.out.println("2. Ajouter des observations");
        System.out.println("3. Gérer les soins");
        System.out.println("4. Vérifier les ordonnances");
    }

    public void consulterDossierPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID du patient: ");
        String patientId = sc.nextLine();

        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.afficherDossier();
        } else {
            System.out.println("❌ Dossier non trouvé");
        }
    }

    public void ajouterObservations() {
        System.out.println("📝 Ajout d'observations infirmières...");
        // Implémentation à compléter
    }
}