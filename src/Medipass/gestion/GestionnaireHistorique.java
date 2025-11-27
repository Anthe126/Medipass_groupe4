package Medipass.gestion;

import java.util.ArrayList;
import java.util.Date;

public class GestionnaireHistorique {
    private static ArrayList<String> historique = new ArrayList<>();

    // ✅ AJOUTER UNE ACTION À L'HISTORIQUE
    public static void ajouterAction(String action) {
        String entree = new Date() + " - " + action;
        historique.add(entree);
        System.out.println("📝 Historique: " + action);
    }

    // ✅ AFFICHER TOUT L'HISTORIQUE
    public static void afficherHistorique() {
        System.out.println("\n=== HISTORIQUE DU SYSTÈME ===");
        if (historique.isEmpty()) {
            System.out.println("Aucune action enregistrée");
            return;
        }

        for (int i = 0; i < historique.size(); i++) {
            System.out.println((i + 1) + ". " + historique.get(i));
        }
    }

    // ✅ EFFACER L'HISTORIQUE (admin seulement)
    public static void effacerHistorique() {
        historique.clear();
        System.out.println("✅ Historique effacé");
    }

    // ✅ EXPORTER L'HISTORIQUE EN CSV
    public static void exporterHistoriqueCSV(String nomFichier) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(nomFichier + ".csv");
            writer.write("Date;Action\n");

            for (String action : historique) {
                writer.write(new Date() + ";" + action.replace(";", ",") + "\n");
            }

            writer.close();
            System.out.println("✅ Historique exporté vers " + nomFichier + ".csv");

        } catch (Exception e) {
            System.out.println("❌ Erreur lors de l'export de l'historique: " + e.getMessage());
        }
    }
}