package Medipass.gestion;

import Medipass.dossier.DossierMedical;
import Medipass.dossier.Ordonnance;
import Medipass.patient.Patient;
import Medipass.utilisateur.Utilisateur;
import java.util.*;
import java.io.*;

public class GestionnaireDossiers {
    // ✅ ATTRIBUTS
    private static HashMap<String, DossierMedical> dossiersPatients = new HashMap<>();
    private static ArrayList<DossierMedical> tousLesDossiers = new ArrayList<>();

    // ✅ MÉTHODES DE BASE

    // CRÉER UN DOSSIER POUR UN PATIENT
    public static boolean creerDossierPourPatient(Patient patient, String medecinId) {
        if (dossiersPatients.containsKey(patient.getId())) {
            System.out.println("❌ Ce patient a déjà un dossier médical");
            return false;
        }

        DossierMedical dossier = new DossierMedical(patient.getId());
        dossiersPatients.put(patient.getId(), dossier);
        tousLesDossiers.add(dossier);

        System.out.println("✅ Dossier médical créé pour: " + patient.getPrenom() + " " + patient.getNom());
        GestionnaireHistorique.ajouterAction("Dossier créé pour patient " + patient.getId() + " par " + medecinId);
        return true;
    }

    // TROUVER UN DOSSIER PAR ID PATIENT
    public static DossierMedical trouverDossierPatient(String patientId) {
        return dossiersPatients.get(patientId);
    }

    // SUPPRIMER UN DOSSIER
    public static boolean supprimerDossier(String patientId) {
        if (dossiersPatients.containsKey(patientId)) {
            DossierMedical dossier = dossiersPatients.remove(patientId);
            tousLesDossiers.remove(dossier);
            System.out.println("🗑️ Dossier supprimé pour le patient: " + patientId);
            GestionnaireHistorique.ajouterAction("Dossier supprimé pour patient " + patientId);
            return true;
        }
        System.out.println("❌ Dossier non trouvé pour le patient: " + patientId);
        return false;
    }

    // ✅ MÉTHODES DE RECHERCHE

    // LISTER TOUS LES DOSSIERS
    public static void listerTousLesDossiers() {
        System.out.println("\n=== LISTE DES DOSSIERS MÉDICAUX ===");
        if (dossiersPatients.isEmpty()) {
            System.out.println("Aucun dossier médical dans le système");
            return;
        }

        for (String patientId : dossiersPatients.keySet()) {
            DossierMedical dossier = dossiersPatients.get(patientId);
            System.out.println("- Patient: " + patientId +
                    " | Créé le: " + dossier.getDateDeCreation() +
                    " | Modifié le: " + dossier.getDateDerniereModification());
        }
    }

    // RECHERCHER DES DOSSIERS PAR CRITÈRE
    public static void rechercherDossiers(String critere) {
        System.out.println("\n🔍 RECHERCHE DE DOSSIERS: '" + critere + "'");
        boolean trouve = false;

        for (DossierMedical dossier : tousLesDossiers) {
            if (dossier.getId().toLowerCase().contains(critere.toLowerCase()) ||
                    dossier.getIdPatient().toLowerCase().contains(critere.toLowerCase())) {

                System.out.println("• " + dossier.getId() + " - Patient: " + dossier.getIdPatient() +
                        " - Créé: " + dossier.getDateDeCreation());
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Aucun dossier trouvé pour: " + critere);
        }
    }

    // ✅ MÉTHODES POUR LES ORDONNANCES

    // AJOUTER UNE ORDONNANCE À UN DOSSIER
    public static boolean ajouterOrdonnanceAuDossier(String patientId, Ordonnance ordonnance) {
        DossierMedical dossier = trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.ajouterOrdonnance(ordonnance);
            return true;
        }
        System.out.println("❌ Dossier non trouvé pour le patient: " + patientId);
        return false;
    }

    // OBTENIR LES ORDONNANCES D'UN PATIENT
    public static ArrayList<Ordonnance> getOrdonnancesPatient(String patientId) {
        DossierMedical dossier = trouverDossierPatient(patientId);
        if (dossier != null) {
            return dossier.getOrdonnances();
        }
        return new ArrayList<>();
    }

    // OBTENIR LES ORDONNANCES ACTIVES D'UN PATIENT
    public static ArrayList<Ordonnance> getOrdonnancesActivesPatient(String patientId) {
        ArrayList<Ordonnance> ordonnancesActives = new ArrayList<>();
        DossierMedical dossier = trouverDossierPatient(patientId);

        if (dossier != null) {
            for (Ordonnance ord : dossier.getOrdonnances()) {
                if (ord.estActive()) {
                    ordonnancesActives.add(ord);
                }
            }
        }

        return ordonnancesActives;
    }

    // ✅ MÉTHODES POUR LES STATISTIQUES

    // STATISTIQUES GÉNÉRALES
    public static void afficherStatistiquesGenerales() {
        System.out.println("\n📊 STATISTIQUES DES DOSSIERS MÉDICAUX");
        System.out.println("Total de dossiers: " + tousLesDossiers.size());

        if (tousLesDossiers.isEmpty()) return;

        // Calculer les moyennes
        int totalOrdonnances = 0;
        int totalConsultations = 0;
        int totalAntecedants = 0;
        int totalAllergies = 0;

        for (DossierMedical dossier : tousLesDossiers) {
            totalOrdonnances += dossier.getOrdonnances().size();
            totalConsultations += dossier.getConsultations().size();
            totalAntecedants += dossier.getAntecedants().size();
            totalAllergies += dossier.getAllergies().size();
        }

        System.out.println("📋 Moyennes par dossier:");
        System.out.printf("  • Ordonnances: %.1f\n", (double) totalOrdonnances / tousLesDossiers.size());
        System.out.printf("  • Consultations: %.1f\n", (double) totalConsultations / tousLesDossiers.size());
        System.out.printf("  • Antécédents: %.1f\n", (double) totalAntecedants / tousLesDossiers.size());
        System.out.printf("  • Allergies: %.1f\n", (double) totalAllergies / tousLesDossiers.size());
    }

    // DOSSIERS LES PLUS ACTIFS
    public static void afficherDossiersPlusActifs() {
        System.out.println("\n🏆 DOSSIERS LES PLUS ACTIFS");

        // Trier par date de modification
        tousLesDossiers.sort((d1, d2) ->
                d2.getDateDerniereModification().compareTo(d1.getDateDerniereModification()));

        int limit = Math.min(5, tousLesDossiers.size());
        for (int i = 0; i < limit; i++) {
            DossierMedical dossier = tousLesDossiers.get(i);
            System.out.println((i + 1) + ". " + dossier.getId() +
                    " - Dernière modif: " + dossier.getDateDerniereModification());
        }
    }

    // ✅ MÉTHODES D'IMPORT/EXPORT

    // EXPORTER TOUS LES DOSSIERS EN CSV
    public static void exporterTousDossiersCSV() {
        try {
            String timestamp = new Date().toString().replace(" ", "_").replace(":", "-");
            String nomFichier = "export_dossiers_complet_" + timestamp;
            FileWriter writer = new FileWriter(nomFichier + ".csv");

            // En-tête CSV
            writer.write("DossierID;PatientID;DateCreation;DateModification;NbOrdonnances;NbConsultations;NbAntecedants;NbAllergies\n");

            int count = 0;
            for (DossierMedical dossier : tousLesDossiers) {
                writer.write(String.format("%s;%s;%s;%s;%d;%d;%d;%d\n",
                        dossier.getId(),
                        dossier.getIdPatient(),
                        dossier.getDateDeCreation(),
                        dossier.getDateDerniereModification(),
                        dossier.getOrdonnances().size(),
                        dossier.getConsultations().size(),
                        dossier.getAntecedants().size(),
                        dossier.getAllergies().size()
                ));
                count++;
            }

            writer.close();
            System.out.println("✅ " + count + " dossiers exportés vers " + nomFichier + ".csv");
            GestionnaireHistorique.ajouterAction("Export CSV de " + count + " dossiers");

        } catch (Exception e) {
            System.out.println("❌ Erreur lors de l'export: " + e.getMessage());
        }
    }

    // EXPORTER UN DOSSIER SPÉCIFIQUE
    public static void exporterDossierCSV(String patientId, String nomFichier) {
        DossierMedical dossier = trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.exporterOrdonnancesCSV(nomFichier);
        } else {
            System.out.println("❌ Dossier non trouvé: " + patientId);
        }
    }

    // ✅ MÉTHODES DE VALIDATION - VERSION SIMPLIFIÉE SANS CASTING

    // VÉRIFIER SI UN DOSSIER EXISTE
    public static boolean dossierExiste(String patientId) {
        return dossiersPatients.containsKey(patientId);
    }

    // VÉRIFIER L'ACCÈS AU DOSSIER - VERSION SIMPLIFIÉE
    public static boolean verifierAccesDossier(String patientId, Utilisateur utilisateur) {
        DossierMedical dossier = trouverDossierPatient(patientId);
        if (dossier == null) return false;

        // Vérification simplifiée :
        // - Si l'utilisateur a le même ID que le patient, il a accès
        // - Sinon, on considère que c'est un professionnel de santé
        return utilisateur.getId().equals(patientId) ||
                estProfessionnelDeSante(utilisateur);
    }

    // ✅ MÉTHODE POUR VÉRIFIER SI C'EST UN PROFESSIONNEL DE SANTÉ
    private static boolean estProfessionnelDeSante(Utilisateur utilisateur) {
        // Vérifier le type par le nom de classe pour éviter les imports
        String className = utilisateur.getClass().getSimpleName();
        return className.equals("Medecin") ||
                className.equals("Infirmier") ||
                className.equals("Pharmacien") ||
                className.equals("Administrateur");
    }

    // ✅ MÉTHODES DE MAINTENANCE

    // NETTOYER LES DOSSIERS SANS PATIENT
    public static void nettoyerDossiersOrphelins(List<Patient> patientsExistants) {
        System.out.println("\n🧹 NETTOYAGE DES DOSSIERS ORPHELINS");

        Set<String> idsPatientsExistants = new HashSet<>();
        for (Patient patient : patientsExistants) {
            idsPatientsExistants.add(patient.getId());
        }

        List<String> dossiersASupprimer = new ArrayList<>();
        for (String patientId : dossiersPatients.keySet()) {
            if (!idsPatientsExistants.contains(patientId)) {
                dossiersASupprimer.add(patientId);
            }
        }

        for (String patientId : dossiersASupprimer) {
            supprimerDossier(patientId);
        }

        System.out.println("✅ " + dossiersASupprimer.size() + " dossiers orphelins supprimés");
    }

    // SAUVEGARDER L'ÉTAT DES DOSSIERS
    public static void sauvegarderEtat() {
        try {
            FileOutputStream fos = new FileOutputStream("sauvegarde_dossiers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dossiersPatients);
            oos.close();
            System.out.println("💾 État des dossiers sauvegardé");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    // CHARGER L'ÉTAT DES DOSSIERS
    @SuppressWarnings("unchecked")
    public static void chargerEtat() {
        try {
            FileInputStream fis = new FileInputStream("sauvegarde_dossiers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dossiersPatients = (HashMap<String, DossierMedical>) ois.readObject();
            tousLesDossiers.clear();
            tousLesDossiers.addAll(dossiersPatients.values());
            ois.close();
            System.out.println("📂 État des dossiers chargé: " + dossiersPatients.size() + " dossiers");
        } catch (Exception e) {
            System.out.println("❌ Aucune sauvegarde trouvée ou erreur de chargement");
        }
    }

    // ✅ GETTERS

    public static HashMap<String, DossierMedical> getDossiersPatients() {
        return dossiersPatients;
    }

    public static ArrayList<DossierMedical> getTousLesDossiers() {
        return tousLesDossiers;
    }

    public static int getNombreDossiers() {
        return dossiersPatients.size();
    }
}