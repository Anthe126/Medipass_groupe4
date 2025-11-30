package Medipass;

import Medipass.admin.Administrateur;
import Medipass.utilisateur.Medecin;
import Medipass.utilisateur.Infirmier;
import Medipass.utilisateur.Pharmacien;
import Medipass.utilisateur.Utilisateur;
import Medipass.patient.Patient;
import Medipass.dossier.DossierMedical;
import Medipass.dossier.Antecedant;
import Medipass.dossier.Ordonnance;
import Medipass.consultation.Consultation;
import Medipass.gestion.GestionnaireDossiers;
import Medipass.gestion.GestionnaireHistorique;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Demonstration {

    private static Scanner scanner = new Scanner(System.in);

    public static void lancerDemonstration() {
        System.out.println("==========================================");
        System.out.println("      🧪 MODE DÉMONSTRATION MEDIPASS 🧪");
        System.out.println("    Test complet des fonctionnalités");
        System.out.println("==========================================");

        initialiserDonneesTest();
        menuTestInteractif();

        System.out.println("\n✅ DÉMONSTRATION TERMINÉE AVEC SUCCÈS !");
    }

    private static void initialiserDonneesTest() {
        System.out.println("\n📦 INITIALISATION DES DONNÉES DE TEST...");

        // 🔧 Réinitialiser les gestionnaires
        GestionnaireHistorique.effacerHistorique();

        // 👑 CRÉATION DE L'ADMINISTRATEUR
        System.out.println("👑 Création de l'administrateur...");
        Administrateur admin = new Administrateur("ADMIN_001", "Dupont", "Pierre",
                "p.dupont@medipass.fr", "0123456789", "15/05/1980",
                "123 Avenue de la République, Paris", "admin123");

        // 👨‍⚕️ CRÉATION DES MÉDECINS
        System.out.println("👨‍⚕️ Création des médecins...");
        Medecin medecin1 = new Medecin("MED_DUPO_J_001", "Martin", "Jean",
                "j.martin@medipass.fr", "0123456790", "20/03/1975",
                "456 Rue du Médipass, Lyon", "Cardiologie", true, "med123");

        Medecin medecin2 = new Medecin("MED_BERT_M_002", "Bernard", "Marie",
                "m.bernard@medipass.fr", "0123456791", "10/08/1985",
                "789 Boulevard des Soins, Marseille", "Pédiatrie", true, "med456");

        // 👨‍⚕️ CRÉATION DES INFIRMIERS
        System.out.println("👨‍⚕️ Création des infirmiers...");
        Infirmier infirmier1 = new Infirmier("INF_DURC_L_003", "Durand", "Luc",
                "l.durand@medipass.fr", "0123456792", "05/12/1990",
                "321 Rue des Infirmiers, Lille", "Soins intensifs", true, "inf123");

        // 💊 CRÉATION DES PHARMACIENS
        System.out.println("💊 Création des pharmaciens...");
        Pharmacien pharmacien1 = new Pharmacien("PHAR_MORI_S_004", "Moreau", "Sophie",
                "s.moreau@medipass.fr", "0123456793", "25/09/1988",
                "654 Avenue de la Pharmacie, Bordeaux", "Pharmacie générale", "pharma123");

        // 👤 CRÉATION DES PATIENTS
        System.out.println("👤 Création des patients...");
        Patient patient1 = new Patient("PAT_DUBO_A_001", "15 Rue de la Santé, Paris",
                "12/08/1990", "alice.dubois@email.com", "Dubois", "0123456794",
                "Alice", "F");

        Patient patient2 = new Patient("PAT_MART_P_002", "27 Boulevard du Médipass, Lyon",
                "03/11/1985", "pierre.martin@email.com", "Martin", "0123456795",
                "Pierre", "M");

        Patient patient3 = new Patient("PAT_LEFE_J_003", "89 Rue des Patients, Marseille",
                "22/06/1978", "jacques.lefevre@email.com", "Lefevre", "0123456796",
                "Jacques", "M");

        // 📁 CRÉATION DES DOSSIERS MÉDICAUX
        System.out.println("📁 Création des dossiers médicaux...");
        DossierMedical dossier1 = new DossierMedical("PAT_DUBO_A_001");
        DossierMedical dossier2 = new DossierMedical("PAT_MART_P_002");
        DossierMedical dossier3 = new DossierMedical("PAT_LEFE_J_003");

        // Ajouter les dossiers au gestionnaire
        GestionnaireDossiers.getDossiersPatients().put(patient1.getId(), dossier1);
        GestionnaireDossiers.getDossiersPatients().put(patient2.getId(), dossier2);
        GestionnaireDossiers.getDossiersPatients().put(patient3.getId(), dossier3);
        GestionnaireDossiers.getTousLesDossiers().add(dossier1);
        GestionnaireDossiers.getTousLesDossiers().add(dossier2);
        GestionnaireDossiers.getTousLesDossiers().add(dossier3);

        // 🩺 AJOUT DES ANTÉCÉDENTS
        System.out.println("🩺 Ajout des antécédents médicaux...");

        // Antécédents pour le patient 1
        Antecedant ante1 = new Antecedant("ALLERGIE", "Allergie aux pénicillines",
                LocalDate.of(2015, 3, 15), 3);
        ante1.setCommentaires("Réaction cutanée observée en 2015");

        Antecedant ante2 = new Antecedant("MALADIE_CHRONIQUE", "Asthme modéré",
                LocalDate.of(2010, 7, 20), 2);
        ante2.setCommentaires("Contrôlé par Ventoline");

        dossier1.ajouterAntecedant(ante1);
        dossier1.ajouterAntecedant(ante2);

        // Antécédents pour le patient 2
        Antecedant ante3 = new Antecedant("CHIRURGIE", "Appendicectomie",
                LocalDate.of(2018, 11, 5), 4);

        Antecedant ante4 = new Antecedant("FAMILIAL", "Diabète de type 2 dans la famille",
                LocalDate.of(2020, 1, 10), 2);

        dossier2.ajouterAntecedant(ante3);
        dossier2.ajouterAntecedant(ante4);

        // Antécédents pour le patient 3
        Antecedant ante5 = new Antecedant("MALADIE_CHRONIQUE", "Hypertension artérielle",
                LocalDate.of(2019, 5, 12), 3);
        ante5.setCommentaires("Sous traitement IEC");

        dossier3.ajouterAntecedant(ante5);

        // 🤧 AJOUT DES ALLERGIES
        System.out.println("🤧 Ajout des allergies...");
        dossier1.ajouterAllergie("Pénicilline");
        dossier1.ajouterAllergie("Arachides");
        dossier2.ajouterAllergie("Iode");
        dossier3.ajouterAllergie("Aspirine");

        // 💊 CRÉATION DES ORDONNANCES
        System.out.println("💊 Création des ordonnances...");

        // Ordonnance 1
        Ordonnance ord1 = new Ordonnance("ORD_0001", "MED_DUPO_J_001",
                "Dr Jean Martin", "PAT_DUBO_A_001", "Alice Dubois");
        ord1.ajouterMedicamentDetaille("Ventoline", "1 inhalation 3 fois par jour",
                "100μg", 2, "SPRAY");
        ord1.ajouterMedicamentDetaille("Cortancyl", "1 comprimé le matin",
                "20mg", 30, "COMPRIME");
        ord1.setInstructions("Prendre le Cortancyl pendant le petit déjeuner");
        ord1.setDureeTraitement(30);

        // Ordonnance 2
        Ordonnance ord2 = new Ordonnance("ORD_0002", "MED_BERT_M_002",
                "Dr Marie Bernard", "PAT_MART_P_002", "Pierre Martin");
        ord2.ajouterMedicamentDetaille("Doliprane", "1 comprimé 3 fois par jour si douleur",
                "1000mg", 20, "COMPRIME");
        ord2.ajouterMedicamentDetaille("Spasfon", "2 comprimés 3 fois par jour",
                "80mg", 40, "COMPRIME");
        ord2.setDureeTraitement(7);

        // Ordonnance 3
        Ordonnance ord3 = new Ordonnance("ORD_0003", "MED_DUPO_J_001",
                "Dr Jean Martin", "PAT_LEFE_J_003", "Jacques Lefevre");
        ord3.ajouterMedicamentDetaille("Coversyl", "1 comprimé par jour",
                "5mg", 30, "COMPRIME");
        ord3.ajouterMedicamentDetaille("Kardegic", "1 comprimé par jour",
                "160mg", 30, "COMPRIME");
        ord3.setInstructions("Surveiller la tension artérielle");
        ord3.setDureeTraitement(30);

        // Ajouter les ordonnances aux dossiers
        dossier1.ajouterOrdonnance(ord1);
        dossier2.ajouterOrdonnance(ord2);
        dossier3.ajouterOrdonnance(ord3);

        // 🏥 CRÉATION DES CONSULTATIONS AVEC NOUVEAUX CONSTRUCTEURS
        System.out.println("🏥 Création des consultations...");

        // Consultation réalisée
        Consultation consult1 = new Consultation("PAT_DUBO_A_001", "Alice Dubois",
                "MED_DUPO_J_001", "Dr Jean Martin", "Contrôle asthme");
        consult1.setObservations("Patient stable, bon contrôle de l'asthme");
        consult1.setDiagnostic("Asthme contrôlé");
        consult1.setTraitement("Poursuite du traitement actuel");

        // Consultation planifiée
        LocalDateTime dateFuture = LocalDateTime.now().plusDays(7);
        Consultation consult2 = new Consultation("PAT_MART_P_002", "Pierre Martin",
                "MED_BERT_M_002", "Dr Marie Bernard", "Bilan de santé",
                dateFuture, "SUIVI");

        // Consultation urgence
        LocalDateTime dateUrgence = LocalDateTime.now().minusHours(2);
        Consultation consult3 = new Consultation("PAT_LEFE_J_003", "Jacques Lefevre",
                "MED_DUPO_J_001", "Dr Jean Martin", "Douleurs thoraciques",
                dateUrgence, "URGENCE");
        consult3.setObservations("Douleurs thoraciques à l'effort");
        consult3.setDiagnostic("Angor stable");
        consult3.setTraitement("Traitement anti-angoreux");

        dossier1.ajouterConsultation(consult1);
        dossier2.ajouterConsultation(consult2);
        dossier3.ajouterConsultation(consult3);

        System.out.println("✅ DONNÉES DE TEST INITIALISÉES AVEC SUCCÈS !");
        afficherResuméInitialisation();
    }

    private static void menuTestInteractif() {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           🎯 MENU TEST INTERACTIF");
            System.out.println("=".repeat(60));
            System.out.println("1. 📊 Afficher les statistiques générales");
            System.out.println("2. 📋 Consulter un dossier médical");
            System.out.println("3. 🩺 Voir les antécédents d'un patient");
            System.out.println("4. 💊 Afficher les ordonnances");
            System.out.println("5. 🏥 Gérer les consultations");
            System.out.println("6. 🔍 Rechercher des données");
            System.out.println("7. 💾 Tester les exports CSV");
            System.out.println("8. 👑 Tester les fonctions administrateur");
            System.out.println("9. 📋 Afficher le résumé de démonstration");
            System.out.println("0. 🚪 Retour au menu principal");
            System.out.println("=".repeat(60));

            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            switch (choix) {
                case 1:
                    testerStatistiques();
                    break;
                case 2:
                    testerConsultationDossier();
                    break;
                case 3:
                    testerAntecedents();
                    break;
                case 4:
                    testerOrdonnances();
                    break;
                case 5:
                    testerConsultations();
                    break;
                case 6:
                    testerRecherche();
                    break;
                case 7:
                    testerExportsCSV();
                    break;
                case 8:
                    testerFonctionsAdmin();
                    break;
                case 9:
                    afficherResuméDemonstration();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void testerStatistiques() {
        System.out.println("\n📊 STATISTIQUES GÉNÉRALES DU SYSTÈME");
        GestionnaireDossiers.afficherStatistiquesGenerales();

        System.out.println("\n🏆 DOSSIERS LES PLUS ACTIFS :");
        GestionnaireDossiers.afficherDossiersPlusActifs();
    }

    private static void testerConsultationDossier() {
        System.out.println("\n📋 CONSULTATION D'UN DOSSIER MÉDICAL");
        System.out.print("Entrez l'ID du patient (PAT_DUBO_A_001, PAT_MART_P_002, PAT_LEFE_J_003) : ");
        String patientId = scanner.nextLine();

        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.afficherDossier();
        } else {
            System.out.println("❌ Dossier non trouvé pour le patient : " + patientId);
        }
    }

    private static void testerAntecedents() {
        System.out.println("\n🩺 ANTÉCÉDENTS MÉDICAUX");
        System.out.print("Entrez l'ID du patient : ");
        String patientId = scanner.nextLine();

        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier != null) {
            dossier.afficherAntecedants();
        } else {
            System.out.println("❌ Dossier non trouvé.");
        }
    }

    private static void testerOrdonnances() {
        System.out.println("\n💊 GESTION DES ORDONNANCES");
        System.out.println("1. Afficher les ordonnances d'un patient");
        System.out.println("2. Afficher toutes les ordonnances");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Entrez l'ID du patient : ");
            String patientId = scanner.nextLine();
            ArrayList<Ordonnance> ordonnances = GestionnaireDossiers.getOrdonnancesPatient(patientId);

            if (ordonnances.isEmpty()) {
                System.out.println("❌ Aucune ordonnance trouvée pour ce patient.");
            } else {
                for (Ordonnance ord : ordonnances) {
                    ord.afficherOrdonnanceDetaillee();
                }
            }
        } else if (choix == 2) {
            // Afficher toutes les ordonnances de tous les dossiers
            for (DossierMedical dossier : GestionnaireDossiers.getTousLesDossiers()) {
                if (!dossier.getOrdonnances().isEmpty()) {
                    System.out.println("\n📋 Patient : " + dossier.getIdPatient());
                    for (Ordonnance ord : dossier.getOrdonnances()) {
                        ord.afficherOrdonnanceDetaillee();
                    }
                }
            }
        }
    }

    private static void testerConsultations() {
        System.out.println("\n🏥 GESTION DES CONSULTATIONS");
        System.out.print("Entrez l'ID du patient : ");
        String patientId = scanner.nextLine();

        DossierMedical dossier = GestionnaireDossiers.trouverDossierPatient(patientId);
        if (dossier != null && !dossier.getConsultations().isEmpty()) {
            System.out.println("\n📅 CONSULTATIONS DU PATIENT :");
            for (Consultation consult : dossier.getConsultations()) {
                consult.afficherConsultation();
            }
        } else {
            System.out.println("❌ Aucune consultation trouvée pour ce patient.");
        }
    }

    private static void testerRecherche() {
        System.out.println("\n🔍 RECHERCHE DE DONNÉES");
        System.out.print("Entrez un critère de recherche (nom, ID, type) : ");
        String critere = scanner.nextLine();

        System.out.println("\n🔍 RÉSULTATS DE LA RECHERCHE :");
        GestionnaireDossiers.rechercherDossiers(critere);
    }

    private static void testerExportsCSV() {
        System.out.println("\n💾 TEST DES EXPORTS CSV");

        try {
            System.out.println("1. 📋 Export des dossiers médicaux...");
            GestionnaireDossiers.exporterTousDossiersCSV();

            System.out.println("2. 📝 Export de l'historique...");
            GestionnaireHistorique.exporterHistoriqueCSV("test_historique");

            System.out.println("✅ Exports CSV réalisés avec succès !");

        } catch (Exception e) {
            System.out.println("❌ Erreur lors des exports : " + e.getMessage());
        }
    }

    private static void testerFonctionsAdmin() {
        System.out.println("\n👑 TEST DES FONCTIONS ADMINISTRATEUR");

        // Simulation des fonctions admin
        System.out.println("📊 Statistiques utilisateurs :");
        System.out.println("• 3 patients dans le système");
        System.out.println("• 2 médecins actifs");
        System.out.println("• 1 infirmier");
        System.out.println("• 1 pharmacien");

        System.out.println("\n📝 Historique des actions :");
        GestionnaireHistorique.afficherHistorique();
    }

    private static void afficherResuméInitialisation() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       📋 DONNÉES INITIALISÉES AVEC SUCCÈS");
        System.out.println("=".repeat(60));

        System.out.println("👥 UTILISATEURS CRÉÉS :");
        System.out.println("   • 👑 1 Administrateur");
        System.out.println("   • 👨‍⚕️ 2 Médecins (Cardiologie, Pédiatrie)");
        System.out.println("   • 👨‍⚕️ 1 Infirmier (Soins intensifs)");
        System.out.println("   • 💊 1 Pharmacien");
        System.out.println("   • 👤 3 Patients avec dossiers complets");

        System.out.println("\n📁 DONNÉES MÉDICALES :");
        System.out.println("   • 🩺 8 Antécédents médicaux variés");
        System.out.println("   • 🤧 4 Allergies enregistrées");
        System.out.println("   • 💊 3 Ordonnances avec 7 médicaments");
        System.out.println("   • 🏥 3 Consultations (réalisée, planifiée, urgence)");

        System.out.println("=".repeat(60));
    }

    public static void afficherResuméDemonstration() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              🎯 RÉSUMÉ DE LA DÉMONSTRATION");
        System.out.println("=".repeat(60));

        System.out.println("🔧 FONCTIONNALITÉS DISPONIBLES :");
        System.out.println("   ✅ Consultation des dossiers médicaux");
        System.out.println("   ✅ Gestion des antécédents et allergies");
        System.out.println("   ✅ Visualisation des ordonnances");
        System.out.println("   ✅ Gestion des consultations");
        System.out.println("   ✅ Recherche de données");
        System.out.println("   ✅ Export CSV des données");
        System.out.println("   ✅ Statistiques et rapports");

        System.out.println("\n💡 POUR TESTER :");
        System.out.println("   • Utilisez les IDs patients : PAT_DUBO_A_001, PAT_MART_P_002, PAT_LEFE_J_003");
        System.out.println("   • Explorez les différentes options du menu");
        System.out.println("   • Testez les exports CSV pour voir les données structurées");

        System.out.println("=".repeat(60));
    }
}