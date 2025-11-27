package Medipass.dossier;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ordonnance {
    private String id;
    private String medecinId;
    private String medecinNom;
    private String patientId;
    private String patientNom;
    private Date datePrescription;
    private ArrayList<String> medicaments; // ✅ Garder la structure existante
    private String instructions;
    private int dureeTraitement;
    private String statut;

    // ✅ CONSTRUCTEURS COMPATIBLES
    public Ordonnance(String id, String medecinId, String patientId) {
        this.id = id;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.datePrescription = new Date();
        this.medicaments = new ArrayList<>();
        this.statut = "ACTIVE";
    }

    // Nouveau constructeur amélioré
    public Ordonnance(String id, String medecinId, String medecinNom, String patientId, String patientNom) {
        this.id = id;
        this.medecinId = medecinId;
        this.medecinNom = medecinNom;
        this.patientId = patientId;
        this.patientNom = patientNom;
        this.datePrescription = new Date();
        this.medicaments = new ArrayList<>();
        this.statut = "ACTIVE";
    }

    public Ordonnance() {
        this.medicaments = new ArrayList<>();
        this.datePrescription = new Date();
        this.statut = "ACTIVE";
    }

    // ✅ GARDER LA MÉTHODE EXISTANTE (compatible)
    public void ajouterMedicament(String medicament, String posologie) {
        String prescription = medicament + " - " + posologie;
        medicaments.add(prescription);
    }

    // ✅ NOUVELLE MÉTHODE AMÉLIORÉE
    public void ajouterMedicamentDetaille(String nom, String posologie, String dosage, int quantite, String forme) {
        String prescription = String.format("%s (%s) - %s - Quantité: %d - Forme: %s",
                nom, dosage, posologie, quantite, forme);
        medicaments.add(prescription);
        System.out.println("✅ Médicament ajouté : " + nom);
    }

    // ✅ MÉTHODE INTERACTIVE POUR AJOUTER UN MÉDICAMENT
    public void ajouterMedicamentInteractif() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== AJOUT D'UN MÉDICAMENT ===");
        System.out.print("Nom du médicament : ");
        String nom = sc.nextLine();

        System.out.print("Posologie (ex: 1 comprimé 3 fois par jour) : ");
        String posologie = sc.nextLine();

        System.out.print("Dosage (ex: 500mg, 10ml) : ");
        String dosage = sc.nextLine();

        System.out.print("Quantité : ");
        int quantite = sc.nextInt();
        sc.nextLine();

        System.out.print("Forme (COMPRIME/SIROP/POMMADE/INJECTION) : ");
        String forme = sc.nextLine();

        ajouterMedicamentDetaille(nom, posologie, dosage, quantite, forme);
    }

    // ✅ MÉTHODES DE GESTION DU STATUT
    public void marquerCommeTerminee() {
        this.statut = "TERMINEE";
        System.out.println("✅ Ordonnance marquée comme terminée");
    }

    public void annulerOrdonnance() {
        this.statut = "ANNULEE";
        System.out.println("❌ Ordonnance annulée");
    }

    public boolean estActive() {
        return "ACTIVE".equals(statut);
    }

    // ✅ MÉTHODE D'AFFICHAGE AMÉLIORÉE
    public void afficherOrdonnanceDetaillee() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  💊 ORDONNANCE MÉDICALE");
        System.out.println("=".repeat(60));
        System.out.println("N° : " + id);
        System.out.println("Date : " + datePrescription);
        System.out.println("Médecin : " + (medecinNom != null ? medecinNom : medecinId));
        System.out.println("Patient : " + (patientNom != null ? patientNom : patientId));
        System.out.println("Statut : " + getStatutFormate());
        System.out.println("Durée du traitement : " + dureeTraitement + " jours");
        System.out.println("-".repeat(60));

        System.out.println("MÉDICAMENTS PRESCRITS :");
        if (medicaments.isEmpty()) {
            System.out.println("  Aucun médicament prescrit");
        } else {
            for (int i = 0; i < medicaments.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + medicaments.get(i));
            }
        }

        if (instructions != null && !instructions.isEmpty()) {
            System.out.println("\nINSTRUCTIONS :");
            System.out.println("  " + instructions);
        }

        System.out.println("=".repeat(60));
    }

    // ✅ MÉTHODE EXISTANTE (à garder pour compatibilité)
    public void afficherOrdonnancesPatient() {
        System.out.println("\n📋 Ordonnance du " + datePrescription);
        System.out.println("Par le Dr. " + (medecinNom != null ? medecinNom : medecinId));

        for (int i = 0; i < medicaments.size(); i++) {
            System.out.println("💊 " + medicaments.get(i));
        }
    }

    // ✅ MÉTHODES FORMATTÉES
    public String getStatutFormate() {
        switch (statut) {
            case "ACTIVE": return "🟢 Active";
            case "TERMINEE": return "🔵 Terminée";
            case "ANNULEE": return "🔴 Annulée";
            default: return statut;
        }
    }

    // ✅ GETTERS ET SETTERS (garder ceux existants + ajouter nouveaux)
    public String getId() { return id; }
    public String getMedecinId() { return medecinId; }
    public String getPatientId() { return patientId; }
    public ArrayList<String> getMedicaments() { return medicaments; }
    public Date getDatePrescription() { return datePrescription; }

    // Nouveaux getters/setters
    public String getMedecinNom() { return medecinNom; }
    public void setMedecinNom(String medecinNom) { this.medecinNom = medecinNom; }

    public String getPatientNom() { return patientNom; }
    public void setPatientNom(String patientNom) { this.patientNom = patientNom; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public int getDureeTraitement() { return dureeTraitement; }
    public void setDureeTraitement(int dureeTraitement) { this.dureeTraitement = dureeTraitement; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    @Override
    public String toString() {
        return String.format("Ordonnance %s - %s - %s médicaments",
                id, datePrescription, medicaments.size());
    }

        // ✅ MÉTHODE POUR EXPORTER EN CSV
        public String toCSV() {
            StringBuilder csv = new StringBuilder();
            csv.append(String.format("%s;%s;%s;%s;%s;%s;%d;%s;%s",
                    id,
                    medecinId != null ? medecinId : "",
                    medecinNom != null ? medecinNom : "",
                    patientId != null ? patientId : "",
                    patientNom != null ? patientNom : "",
                    datePrescription != null ? datePrescription.toString() : "",
                    dureeTraitement,
                    instructions != null ? instructions.replace(";", ",") : "",
                    statut != null ? statut : "ACTIVE"
            ));

            // Ajouter les médicaments
            for (String medicament : medicaments) {
                csv.append("\n").append(id).append(";MEDICAMENT;").append(medicament.replace(";", ","));
            }

            return csv.toString();
        }

        // ✅ MÉTHODE POUR IMPORTER DEPUIS CSV
        public static Ordonnance fromCSV(String csvLine) {
            String[] data = csvLine.split(";");
            if (data.length >= 9 && !data[0].equals("ID") && !data[0].contains("MEDICAMENT")) {
                try {
                    Ordonnance ordonnance = new Ordonnance();
                    ordonnance.id = data[0];
                    ordonnance.medecinId = data[1];
                    ordonnance.medecinNom = data[2];
                    ordonnance.patientId = data[3];
                    ordonnance.patientNom = data[4];

                    // Parsing de la date
                    try {
                        ordonnance.datePrescription = new Date(Date.parse(data[5]));
                    } catch (Exception e) {
                        ordonnance.datePrescription = new Date();
                    }

                    ordonnance.dureeTraitement = Integer.parseInt(data[6]);
                    ordonnance.instructions = data[7];
                    ordonnance.statut = data[8];
                    ordonnance.medicaments = new ArrayList<>();

                    return ordonnance;
                } catch (Exception e) {
                    System.out.println("❌ Erreur lors du parsing de l'ordonnance: " + e.getMessage());
                    return null;
                }
            }
            return null;
        }

        // ✅ MÉTHODE POUR AJOUTER UN MÉDICAMENT DEPUIS CSV
        public void ajouterMedicamentFromCSV(String csvLine) {
            String[] data = csvLine.split(";");
            if (data.length >= 3 && data[0].equals(this.id) && data[1].equals("MEDICAMENT")) {
                this.medicaments.add(data[2]);
            }
        }

}