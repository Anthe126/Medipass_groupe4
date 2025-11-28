
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

    public void creerPatient() {
        SystemeMedipass.ajouterPatient();
    }

    // ✅ MÉTHODES POUR LES CONSULTATIONS
    // ✅ MÉTHODE STATIQUE POUR PRESCRIRE UNE ORDONNANCE
    public static void prescrireOrdonnanceStatic() {
        SystemeMedipass.prescrireMedicament(null);
    }

    // ✅ MÉTHODE STATIQUE POUR CRÉER UNE CONSULTATION
    public static void creerConsultationStatic() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🩺 CRÉATION D'UNE CONSULTATION");
        System.out.println("=".repeat(50));

        System.out.print("ID du patient : ");
        String patientId = sc.nextLine();

        // Vérifier que le patient a un dossier
        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier == null) {
            System.out.println("❌ Patient non trouvé ou sans dossier médical");
            return;
        }

        // Demander l'ID du médecin
        System.out.print("ID du médecin : ");
        String medecinId = sc.nextLine();
        System.out.print("Nom du médecin : ");
        String medecinNom = sc.nextLine();

        // Créer la consultation dans le dossier
        dossier.creerConsultationDansDossier(medecinId, medecinNom);
    }


    public void mesConsultations() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        📋 MES CONSULTATIONS");
        System.out.println("=".repeat(50));

        int totalConsultations = 0;

        // Parcourir tous les dossiers pour trouver mes consultations
            /*for (DossierMedical dossier : GestionnaireDossiers.getTousLesDossiers()) {
                ArrayList<Consultation> mesConsultations = dossier.getConsultationsParMedecin(this.id);
                if (!mesConsultations.isEmpty()) {
                    System.out.println("\nPatient : " + dossier.getIdPatient());
                    for (Consultation consultation : mesConsultations) {
                        System.out.println("  • " + consultation.toString());
                        totalConsultations++;
                    }
                }
            }

            if (totalConsultations == 0) {
                System.out.println("Aucune consultation trouvée");
            } else {
                System.out.println("\n📊 Total : " + totalConsultations + " consultation(s)");
            }
        }*/

        /*public void consulterHistoriquePatient () {
            Scanner sc = new Scanner(System.in);

            System.out.print("ID du patient : ");
            String patientId = sc.nextLine();

            DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
            if (dossier != null) {
                System.out.println("\n📅 HISTORIQUE DES CONSULTATIONS");
                dossier.afficherConsultationsDetaillees();
            } else {
                System.out.println("❌ Dossier non trouvé");
            }
        }*/


        // ✅ GETTERS ET SETTERS
        //public int getAnneesExperience() { return anneesExperience; }
        //public void setAnneesExperience(int anneesExperience) { this.anneesExperience = anneesExperience; }
    }
}



