package Medipass.consultation;

import Medipass.dossier.Ordonnance;
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
    private String patientNom;
    private String professionnelNom;
    private ArrayList<Ordonnance> ordonnances;

    // 🔥 CONSTRUCTEURS CORRIGÉS
    public Consultation(String patientNom, String professionnelNom, String motif) {
        this.idConsultation = compteurId++;
        this.dateConsultation = LocalDateTime.now();
        this.patientNom = patientNom;
        this.professionnelNom = professionnelNom;
        this.motif = motif;
        this.observations = ""; // Initialisation correcte
        this.diagnostic = "";
        this.traitement = "";
        this.ordonnances = new ArrayList<>();
    }

    public Consultation(String patientNom, String professionnelNom, String motif, LocalDateTime date) {
        this.idConsultation = compteurId++;
        this.dateConsultation = date;
        this.patientNom = patientNom;
        this.professionnelNom = professionnelNom;
        this.motif = motif;
        this.observations = "";
        this.diagnostic = "";
        this.traitement = "";
        this.ordonnances = new ArrayList<>();
    }

    // 🔥 MÉTHODES DE GESTION CORRIGÉES
    public void saisirObservations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir vos observations: ");
        String nouvellesObservations = sc.nextLine();

        if (this.observations.isEmpty()) {
            this.observations = nouvellesObservations;
        } else {
            this.observations += "\n" + nouvellesObservations;
        }
        System.out.println("✅ Observations ajoutées");
    }

    public void saisirDiagnostic() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir votre diagnostic: ");
        this.diagnostic = sc.nextLine();
        System.out.println("✅ Diagnostic ajouté");
    }

    public void saisirTraitement() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le traitement recommandé: ");
        this.traitement = sc.nextLine();
        System.out.println("✅ Traitement ajouté");
    }

    public void ajouterOrdonnance(Ordonnance ordonnance) {
        this.ordonnances.add(ordonnance);
        System.out.println("✅ Ordonnance ajoutée à la consultation");
    }

    // Getters et Setters
    public static int getCompteurId() {
        return compteurId;
    }

    public static void setCompteurId(int compteurId) {
        Consultation.compteurId = compteurId;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getPatientNom() {
        return patientNom;
    }

    public void setPatientNom(String patientNom) {
        this.patientNom = patientNom;
    }

    public String getProfessionnelNom() {
        return professionnelNom;
    }

    public void setProfessionnelNom(String professionnelNom) {
        this.professionnelNom = professionnelNom;
    }

    public ArrayList<Ordonnance> getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(ArrayList<Ordonnance> ordonnances) {
        this.ordonnances = ordonnances;
    }

    // 🔥 MÉTHODE D'AFFICHAGE AMÉLIORÉE
    public void afficherConsultation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n========================================");
        System.out.println("         CONSULTATION N°" + idConsultation);
        System.out.println("========================================");
        System.out.println("Date         : " + dateConsultation.format(formatter));
        System.out.println("Patient      : " + patientNom);
        System.out.println("Professionnel: " + professionnelNom);
        System.out.println("Motif        : " + motif);
        System.out.println("----------------------------------------");
        System.out.println("OBSERVATIONS :");
        System.out.println(observations == null || observations.isEmpty() ? "  (Aucune)" : "  " + observations);
        System.out.println("----------------------------------------");
        System.out.println("DIAGNOSTIC :");
        System.out.println(diagnostic == null || diagnostic.isEmpty() ? "  (Aucun)" : "  " + diagnostic);
        System.out.println("----------------------------------------");
        System.out.println("TRAITEMENT :");
        System.out.println(traitement == null || traitement.isEmpty() ? "  (Aucun)" : "  " + traitement);

        if (!ordonnances.isEmpty()) {
            System.out.println("----------------------------------------");
            System.out.println("ORDONNANCES (" + ordonnances.size() + "):");
            for (int i = 0; i < ordonnances.size(); i++) {
                System.out.println("\n  Ordonnance " + (i + 1) + ":");
                // Note: Vous devrez implémenter afficherOrdonnancesPatient() dans Ordonnance
                System.out.println("  ID: " + ordonnances.get(i).getId());
            }
        }
        System.out.println("========================================\n");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Consultation #" + idConsultation + " - " + dateConsultation.format(formatter) +
                " - Patient: " + patientNom + " - Motif: " + motif;
    }

    // 🔥 MÉTHODE POUR CRÉER UNE CONSULTATION COMPLÈTE
    public static Consultation creerConsultationComplete() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== NOUVELLE CONSULTATION ===");
        System.out.println("Nom du patient: ");
        String patientNom = sc.nextLine();

        System.out.println("Nom du professionnel: ");
        String proNom = sc.nextLine();

        System.out.println("Motif de la consultation: ");
        String motif = sc.nextLine();

        Consultation consultation = new Consultation(patientNom, proNom, motif);

        // Saisie des informations
        System.out.println("\n--- Saisie des observations ---");
        consultation.saisirObservations();

        System.out.println("\n--- Saisie du diagnostic ---");
        consultation.saisirDiagnostic();

        System.out.println("\n--- Saisie du traitement ---");
        consultation.saisirTraitement();

        System.out.println("✅ Consultation créée avec succès!");
        return consultation;
    }
}