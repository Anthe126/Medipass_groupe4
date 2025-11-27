package Medipass.utilisateur;

import Medipass.gestion.GestionnaireDossiers;
import Medipass.dossier.Ordonnance;
import java.util.Scanner;

public class Pharmacien extends ProDeSante {

    public Pharmacien(String id, String nom, String prenom, String email,
                      String numeroTelephone, String dateNaissance,
                      String adresse, String specialite, String motDePasse) {
        super(id, nom, prenom, email, numeroTelephone, dateNaissance, adresse, specialite, motDePasse, "PHARMACIEN");
    }

    public Pharmacien() {
        super();
        this.role = "PHARMACIEN";
    }

    @Override
    public void afficherMenuSpecifique() {
        System.out.println("\n=== MENU PHARMACIEN ===");
        System.out.println("1. Vérifier une ordonnance");
        System.out.println("2. Consulter un dossier patient");
        System.out.println("3. Gérer le stock");
        System.out.println("4. Historique des délivrances");
    }

    public void verifierOrdonnance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID de l'ordonnance: ");
        String ordonnanceId = sc.nextLine();

        System.out.println("🔍 Vérification de l'ordonnance " + ordonnanceId);
        // Implémentation à compléter
    }

    public void consulterOrdonnancesPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID du patient: ");
        String patientId = sc.nextLine();

        var ordonnances = GestionnaireDossiers.getOrdonnancesActivesPatient(patientId);
        if (!ordonnances.isEmpty()) {
            ordonnances.forEach(Ordonnance::afficherOrdonnancesPatient);
        } else {
            System.out.println("❌ Aucune ordonnance active trouvée");
        }
    }
}