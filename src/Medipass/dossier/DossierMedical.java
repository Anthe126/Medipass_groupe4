package Medipass.dossier;

import Medipass.consultation.Consultation;
import java.util.*;
import Medipass.gestion.GestionnaireHistorique;
import java.io.*;

public class DossierMedical {
    // Attributs
    private String id;
    private String idPatient;
    private Date dateDeCreation;
    private Date dateDerniereModification;

    // Contenu medical - correction des noms (minuscules)
    private ArrayList<Ordonnance> ordonnances;
    private ArrayList<Consultation> consultations;
    private ArrayList<Antecedant> antecedants;
    private ArrayList<String> allergies;

    // Constructeurs
    public DossierMedical(String idPatient) {
        this.allergies = new ArrayList<>();
        this.antecedants = new ArrayList<>();
        this.consultations = new ArrayList<>();
        this.ordonnances = new ArrayList<>();
        this.dateDeCreation = new Date();
        this.dateDerniereModification = new Date();
        this.id = "DOSSIER_" + idPatient;
        this.idPatient = idPatient;
    }

    public DossierMedical() {
        this.allergies = new ArrayList<>();
        this.antecedants = new ArrayList<>();
        this.consultations = new ArrayList<>();
        this.ordonnances = new ArrayList<>();
    }

    // Getters
    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public ArrayList<Antecedant> getAntecedants() {
        return antecedants;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public Date getDateDerniereModification() {
        return dateDerniereModification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public ArrayList<Ordonnance> getOrdonnances() {
        return ordonnances;
    }

    // 🔥 FONCTIONS SPÉCIFIQUES CORRIGÉES

    public void ajouterOrdonnance(Ordonnance ordonnance) {
        this.ordonnances.add(ordonnance);
        this.dateDerniereModification = new Date();
        System.out.println("✅ Ordonnance ajoutée au dossier médical");
    }

    public void ajouterConsultation(Consultation consultation) {
        this.consultations.add(consultation);
        this.dateDerniereModification = new Date();
        System.out.println("✅ Consultation ajoutée au dossier médical");
    }

    public void ajouterAntecedant(Antecedant antecedant) {
        this.antecedants.add(antecedant);
        this.dateDerniereModification = new Date();
        System.out.println("✅ Antécédent ajouté au dossier médical");
    }

    public void ajouterAllergie(String allergie) {
        this.allergies.add(allergie);
        this.dateDerniereModification = new Date();
        System.out.println("✅ Allergie '" + allergie + "' ajoutée au dossier médical");
    }

    // Ajout d'antécédent amélioré
    public void ajouterAntecedantInteractif() {
        Antecedant nouvelAntecedant = Antecedant.creerAntecedantInteractif();
        this.antecedants.add(nouvelAntecedant);
        this.dateDerniereModification = new Date();
        System.out.println("✅ Antécédent ajouté au dossier");
    }

    // Affichage amélioré des antécédents
    public void afficherAntecedants() {
        System.out.println("\n=== ANTÉCÉDENTS MÉDICAUX ===");
        if (antecedants.isEmpty()) {
            System.out.println("Aucun antécédent enregistré");
            return;
        }

        for (Antecedant antecedant : antecedants) {
            antecedant.afficherDetails();
        }
    }

    // Filtrer l'antecedant par type
    public void afficherAntecedantsParType(String type) {
        System.out.println("\n=== ANTÉCÉDENTS " + type + " ===");
        for (Antecedant antecedant : antecedants) {
            if (antecedant.getType().equals(type)) {
                System.out.println("• " + antecedant.getDescription() +
                        " (Gravité: " + antecedant.getNiveauGravite() + "/5)");
            }
        }
    }

    // Méthode pour ajouter une allergie via saisie utilisateur
    public void saisirEtAjouterAllergie() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== AJOUT D'UNE ALLERGIE ===");
        System.out.println("Veuillez saisir le nom de l'allergie: ");
        String allergie = sc.nextLine();

        ajouterAllergie(allergie);
    }

    // Méthode d'affichage du dossier
    public void afficherDossier() {
        System.out.println("\n========================================");
        System.out.println("        DOSSIER MÉDICAL - " + id);
        System.out.println("========================================");
        System.out.println("Patient ID: " + idPatient);
        System.out.println("Créé le: " + dateDeCreation);
        System.out.println("Dernière modification: " + dateDerniereModification);

        System.out.println("\n--- ALLERGIES (" + allergies.size() + ") ---");
        if (allergies.isEmpty()) {
            System.out.println("Aucune allergie enregistrée");
        } else {
            for (int i = 0; i < allergies.size(); i++) {
                System.out.println((i + 1) + ". " + allergies.get(i));
            }
        }

        System.out.println("\n--- ANTÉCÉDENTS (" + antecedants.size() + ") ---");
        if (antecedants.isEmpty()) {
            System.out.println("Aucun antécédent enregistré");
        } else {
            for (int i = 0; i < antecedants.size(); i++) {
                Antecedant a = antecedants.get(i);
                System.out.println((i + 1) + ". " + a.getDescription() +
                        " (Date: " + a.getDateDecouverte() +
                        ", Gravité: " + a.getNiveauGravite() + "/5)");
            }
        }

        System.out.println("\n--- CONSULTATIONS (" + consultations.size() + ") ---");
        if (consultations.isEmpty()) {
            System.out.println("Aucune consultation enregistrée");
        } else {
            for (int i = 0; i < consultations.size(); i++) {
                System.out.println((i + 1) + ". " + consultations.get(i).toString());
            }
        }

        System.out.println("\n--- ORDONNANCES (" + ordonnances.size() + ") ---");
        if (ordonnances.isEmpty()) {
            System.out.println("Aucune ordonnance enregistrée");
        } else {
            for (int i = 0; i < ordonnances.size(); i++) {
                System.out.println((i + 1) + ". Ordonnance " + ordonnances.get(i).getId());
            }
        }
        System.out.println("========================================\n");
    }

        // ✅ MÉTHODE EXPORTER ORDONNANCES CSV
        public void exporterOrdonnancesCSV(String nomFichier) {
            try {
                FileWriter writer = new FileWriter(nomFichier + ".csv");

                // En-tête CSV
                writer.write("DossierID;OrdonnanceID;MedecinID;MedecinNom;PatientID;PatientNom;DatePrescription;DureeTraitement;Instructions;Statut\n");

                int count = 0;
                for (Ordonnance ordonnance : ordonnances) {
                    writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%d;%s;%s\n",
                            id,
                            ordonnance.getId(),
                            ordonnance.getMedecinId(),
                            ordonnance.getMedecinNom() != null ? ordonnance.getMedecinNom() : "",
                            ordonnance.getPatientId(),
                            ordonnance.getPatientNom() != null ? ordonnance.getPatientNom() : "",
                            ordonnance.getDatePrescription().toString(),
                            ordonnance.getDureeTraitement(),
                            ordonnance.getInstructions() != null ? ordonnance.getInstructions().replace(";", ",") : "",
                            ordonnance.getStatut() != null ? ordonnance.getStatut() : "ACTIVE"
                    ));

                    // Écrire les médicaments
                    for (String medicament : ordonnance.getMedicaments()) {
                        writer.write(String.format("%s;%s;MEDICAMENT;%s\n",
                                id, ordonnance.getId(), medicament.replace(";", ",")));
                    }
                    count++;
                }

                writer.close();
                System.out.println("✅ " + count + " ordonnances exportées vers " + nomFichier + ".csv");
                GestionnaireHistorique.ajouterAction("Export CSV des ordonnances du dossier " + id);

            } catch (Exception e) {
                System.out.println("❌ Erreur lors de l'export: " + e.getMessage());
            }
        }

        // ... autres méthodes ...

}