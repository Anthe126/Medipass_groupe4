package Medipass.consultation;

import Medipass.SystemeMedipass;
import Medipass.dossier.DossierMedical;
import Medipass.dossier.Ordonnance;
import Medipass.gestion.GestionnaireDossiers;
import Medipass.gestion.GestionnaireHistorique;
import Medipass.utilisateur.Medecin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Consultation {
    private static int compteurId = 1;

    private int idConsultation;
    private LocalDateTime dateConsultation;
    private String motif;
    private String observations;
    private String diagnostic;
    private String traitement;
    private String patientId;
    private String patientNom;
    private String medecinId;
    private String medecinNom;
    private ArrayList<Ordonnance> ordonnances;
    private String statut; // "PLANIFIEE", "REALISEE", "ANNULEE"
    private String type; // "CONSULTATION", "URGENCE", "SUIVI"

    // ✅ CONSTRUCTEURS AMÉLIORÉS
    public Consultation(String patientId, String patientNom, String medecinId, String medecinNom, String motif) {
        this.idConsultation = compteurId++;
        this.dateConsultation = LocalDateTime.now();
        this.patientId = patientId;
        this.patientNom = patientNom;
        this.medecinId = medecinId;
        this.medecinNom = medecinNom;
        this.motif = motif;
        this.observations = "";
        this.diagnostic = "";
        this.traitement = "";
        this.ordonnances = new ArrayList<>();
        this.statut = "REALISEE";
        this.type = "CONSULTATION";
    }

    public Consultation(String patientId, String patientNom, String medecinId, String medecinNom,
                        String motif, LocalDateTime date, String type) {
        this.idConsultation = compteurId++;
        this.dateConsultation = date;
        this.patientId = patientId;
        this.patientNom = patientNom;
        this.medecinId = medecinId;
        this.medecinNom = medecinNom;
        this.motif = motif;
        this.observations = "";
        this.diagnostic = "";
        this.traitement = "";
        this.ordonnances = new ArrayList<>();
        this.statut = "PLANIFIEE";
        this.type = type;
    }

    // ✅ MÉTHODES DE GESTION
    public void ajouterObservations(String nouvellesObservations) {
        if (this.observations.isEmpty()) {
            this.observations = nouvellesObservations;
        } else {
            this.observations += "\n" + nouvellesObservations;
        }
        System.out.println("✅ Observations ajoutées");
    }

    public void definirDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
        System.out.println("✅ Diagnostic enregistré");
    }

    public void prescrireTraitement(String traitement) {
        this.traitement = traitement;
        System.out.println("✅ Traitement prescrit");
    }

    public void ajouterOrdonnance(Ordonnance ordonnance) {
        this.ordonnances.add(ordonnance);
        System.out.println("✅ Ordonnance ajoutée à la consultation");
    }

    public void marquerCommeRealisee() {
        this.statut = "REALISEE";
        System.out.println("✅ Consultation marquée comme réalisée");
    }

    public void annulerConsultation() {
        this.statut = "ANNULEE";
        System.out.println("❌ Consultation annulée");
    }

    // ✅ MÉTHODE DE CALCUL DU PRIX
    private double calculerPrix(String typeConsultation) {
        switch (typeConsultation) {
            case "URGENCE":
                return 80.0;
            case "SUIVI":
                return 35.0;
            case "CONSULTATION":
            default:
                return 50.0;
        }
    }

    // ✅ MÉTHODE POUR CRÉER UNE CONSULTATION INTERACTIVEMENT
    public static Consultation creerConsultationInteractive(String medecinId, String medecinNom) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("        🩺 NOUVELLE CONSULTATION");
        System.out.println("=".repeat(50));

        // Saisie du patient
        System.out.print("ID du patient : ");
        String patientId = sc.nextLine();

        // Vérifier que le patient existe et a un dossier
        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier == null) {
            System.out.println("❌ Patient non trouvé ou sans dossier médical");
            return null;
        }

        String patientNom = "Patient"; // À récupérer du système
        System.out.println("Patient : " + patientNom);

        // Saisie des informations de base
        System.out.print("Motif de la consultation : ");
        String motif = sc.nextLine();

        System.out.print("Type (CONSULTATION/URGENCE/SUIVI) : ");
        String type = sc.nextLine().toUpperCase();

        // Création de la consultation
        Consultation consultation = new Consultation(patientId, patientNom, medecinId, medecinNom, motif, LocalDateTime.now(), type);

        // Saisie des détails
        System.out.println("\n--- OBSERVATIONS ---");
        System.out.print("Observations cliniques : ");
        consultation.ajouterObservations(sc.nextLine());

        System.out.print("Diagnostic : ");
        consultation.definirDiagnostic(sc.nextLine());

        System.out.print("Traitement recommandé : ");
        consultation.prescrireTraitement(sc.nextLine());

        // Demander si une ordonnance est nécessaire
        System.out.print("\n📋 Prescrire une ordonnance ? (o/n) : ");
        String reponseOrdonnance = sc.nextLine().toLowerCase();

        if (reponseOrdonnance.equals("o") || reponseOrdonnance.equals("oui")) {
            System.out.println("💊 Création de l'ordonnance...");
            Medecin.prescrireOrdonnanceStatic();
        }

        consultation.marquerCommeRealisee();

        System.out.println("\n✅ Consultation créée avec succès !");
        GestionnaireHistorique.ajouterAction("Consultation créée pour " + patientNom + " par " + medecinNom);

        return consultation;
    }

    // ✅ MÉTHODE D'AFFICHAGE AMÉLIORÉE
    public void afficherConsultation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  🩺 CONSULTATION MÉDICALE");
        System.out.println("=".repeat(60));
        System.out.println("N° : " + idConsultation);
        System.out.println("Date : " + dateConsultation.format(formatter));
        System.out.println("Statut : " + getStatutFormate());
        System.out.println("Type : " + type);
        System.out.println("Médecin : " + medecinNom + " (ID: " + medecinId + ")");
        System.out.println("Patient : " + patientNom + " (ID: " + patientId + ")");
        System.out.println("Motif : " + motif);
        System.out.println("-".repeat(60));

        System.out.println("OBSERVATIONS :");
        System.out.println(observations.isEmpty() ? "  (Aucune)" : "  " + observations);

        System.out.println("\nDIAGNOSTIC :");
        System.out.println(diagnostic.isEmpty() ? "  (Aucun)" : "  " + diagnostic);

        System.out.println("\nTRAITEMENT :");
        System.out.println(traitement.isEmpty() ? "  (Aucun)" : "  " + traitement);

        if (!ordonnances.isEmpty()) {
            System.out.println("\nORDONNANCES (" + ordonnances.size() + "):");
            for (int i = 0; i < ordonnances.size(); i++) {
                System.out.println("\n  Ordonnance " + (i + 1) + ":");
                ordonnances.get(i).afficherOrdonnancesPatient();
            }
        }
        System.out.println("=".repeat(60));
    }

    // ✅ MÉTHODES FORMATTÉES
    public String getStatutFormate() {
        switch (statut) {
            case "PLANIFIEE": return "🟡 Planifiée";
            case "REALISEE": return "🟢 Réalisée";
            case "ANNULEE": return "🔴 Annulée";
            default: return statut;
        }
    }

    public String getTypeFormate() {
        switch (type) {
            case "URGENCE": return "🚨 Urgence";
            case "SUIVI": return "📋 Suivi";
            case "CONSULTATION": return "🩺 Consultation";
            default: return type;
        }
    }

    // ✅ GETTERS ET SETTERS
    public int getIdConsultation() { return idConsultation; }
    public LocalDateTime getDateConsultation() { return dateConsultation; }
    public String getMotif() { return motif; }
    public String getObservations() { return observations; }
    public String getDiagnostic() { return diagnostic; }
    public String getTraitement() { return traitement; }
    public String getPatientId() { return patientId; }
    public String getPatientNom() { return patientNom; }
    public String getMedecinId() { return medecinId; }
    public String getMedecinNom() { return medecinNom; }
    public ArrayList<Ordonnance> getOrdonnances() { return ordonnances; }
    public String getStatut() { return statut; }
    public String getType() { return type; }

    public static void setCompteurId(int compteurId) {
        Consultation.compteurId = compteurId;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public void setMedecinId(String medecinId) {
        this.medecinId = medecinId;
    }

    public void setMedecinNom(String medecinNom) {
        this.medecinNom = medecinNom;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setOrdonnances(ArrayList<Ordonnance> ordonnances) {
        this.ordonnances = ordonnances;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientNom(String patientNom) {
        this.patientNom = patientNom;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) { this.dateConsultation = dateConsultation; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Consultation #" + idConsultation + " - " + dateConsultation.format(formatter) +
                " - " + patientNom + " - " + motif + " - " + getStatutFormate();
    }
}