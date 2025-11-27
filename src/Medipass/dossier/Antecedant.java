package Medipass.dossier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Antecedant {
    private static int compteurId = 1;

    // Attributs améliorés
    private String id;
    private String type; // "ALLERGIE", "CHIRURGIE", "MALADIE", "TRAITEMENT", "FAMILIAL", etc.
    private String description;
    private LocalDate dateDecouverte;
    private int niveauGravite; // 1-5
    private String commentaires;
    private String statut; // "ACTIF", "RESOLU", "ANCIEN"

    // ✅ CONSTRUCTEURS COMPLETS
    public Antecedant(String type, String description, LocalDate dateDecouverte, int niveauGravite) {
        this.id = "ANT_" + String.format("%04d", compteurId++);
        this.type = type;
        this.description = description;
        this.dateDecouverte = dateDecouverte;
        this.niveauGravite = validerNiveauGravite(niveauGravite);
        this.commentaires = "";
        this.statut = "ACTIF";
    }

    public Antecedant(String type, String description, String dateStr, int niveauGravite) {
        this(type, description, parseDate(dateStr), niveauGravite);
    }

    // Constructeur par défaut
    public Antecedant() {
        this.id = "ANT_" + String.format("%04d", compteurId++);
        this.statut = "ACTIF";
    }

    // ✅ VALIDATION DU NIVEAU DE GRAVITÉ
    private static int validerNiveauGravite(int niveau) {
        if (niveau < 1) return 1;
        if (niveau > 5) return 5;
        return niveau;
    }

    // ✅ PARSING DES DATES AVEC GESTION D'ERREUR
    private static LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Format de date invalide. Utilisation de la date actuelle.");
            return LocalDate.now();
        }
    }

    // ✅ MÉTHODE POUR CRÉER UN ANTÉCÉDENT INTERACTIVEMENT
    public static Antecedant creerAntecedantInteractif() {
        Scanner sc = new Scanner(System.in);
        Antecedant antecedant = new Antecedant();

        System.out.println("\n=== NOUVEL ANTÉCÉDENT ===");

        // Saisie du type
        System.out.println("Type d'antécédent :");
        System.out.println("1. Allergie");
        System.out.println("2. Maladie chronique");
        System.out.println("3. Intervention chirurgicale");
        System.out.println("4. Traitement long");
        System.out.println("5. Antécédent familial");
        System.out.println("6. Autre");
        System.out.print("Choix : ");

        int choixType = sc.nextInt();
        sc.nextLine(); // Vider buffer

        switch (choixType) {
            case 1: antecedant.type = "ALLERGIE"; break;
            case 2: antecedant.type = "MALADIE_CHRONIQUE"; break;
            case 3: antecedant.type = "CHIRURGIE"; break;
            case 4: antecedant.type = "TRAITEMENT_LONG"; break;
            case 5: antecedant.type = "FAMILIAL"; break;
            default: antecedant.type = "AUTRE"; break;
        }

        // Saisie de la description
        System.out.print("Description : ");
        antecedant.description = sc.nextLine();

        // Saisie de la date
        System.out.print("Date de découverte (jj/mm/aaaa) : ");
        String dateStr = sc.nextLine();
        antecedant.dateDecouverte = parseDate(dateStr);

        // Saisie du niveau de gravité
        System.out.print("Niveau de gravité (1-5) : ");
        antecedant.niveauGravite = validerNiveauGravite(sc.nextInt());
        sc.nextLine(); // Vider buffer

        // Saisie des commentaires
        System.out.print("Commentaires (optionnel) : ");
        antecedant.commentaires = sc.nextLine();

        System.out.println("✅ Antécédent créé avec succès !");
        return antecedant;
    }

    // ✅ MÉTHODE POUR METTRE À JOUR LE STATUT
    public void mettreAJourStatut(String nouveauStatut) {
        if (nouveauStatut.equals("ACTIF") || nouveauStatut.equals("RESOLU") || nouveauStatut.equals("ANCIEN")) {
            this.statut = nouveauStatut;
            System.out.println("✅ Statut mis à jour : " + nouveauStatut);
        } else {
            System.out.println("❌ Statut invalide");
        }
    }

    // ✅ MÉTHODE POUR AFFICHER L'ANTÉCÉDENT
    public void afficherDetails() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("📋 ANTÉCÉDENT " + id);
        System.out.println("─".repeat(50));
        System.out.println("Type          : " + getTypeFormate());
        System.out.println("Description   : " + description);
        System.out.println("Date          : " + dateDecouverte.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Gravité       : " + getGraviteFormatee());
        System.out.println("Statut        : " + getStatutFormate());
        if (!commentaires.isEmpty()) {
            System.out.println("Commentaires  : " + commentaires);
        }
        System.out.println("─".repeat(50));
    }

    // ✅ MÉTHODES FORMATTÉES POUR L'AFFICHAGE
    public String getTypeFormate() {
        switch (type) {
            case "ALLERGIE": return "🤧 Allergie";
            case "MALADIE_CHRONIQUE": return "🫀 Maladie chronique";
            case "CHIRURGIE": return "🔪 Intervention chirurgicale";
            case "TRAITEMENT_LONG": return "💊 Traitement long terme";
            case "FAMILIAL": return "👨‍👩‍👧‍👦 Antécédent familial";
            default: return "📝 " + type;
        }
    }

    public String getGraviteFormatee() {
        String gravite = "★".repeat(niveauGravite) + "☆".repeat(5 - niveauGravite);
        return niveauGravite + "/5 " + gravite;
    }

    public String getStatutFormate() {
        switch (statut) {
            case "ACTIF": return "🔴 Actif";
            case "RESOLU": return "🟢 Résolu";
            case "ANCIEN": return "⚫ Ancien";
            default: return statut;
        }
    }

    // ✅ MÉTHODE POUR SAUVEGARDE (futur CSV)
    public String toCSV() {
        return String.format("%s;%s;%s;%s;%d;%s;%s",
                id, type, description,
                dateDecouverte.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                niveauGravite, commentaires, statut);
    }

    // ✅ MÉTHODE POUR CHARGER DEPUIS CSV (futur)
    public static Antecedant fromCSV(String csvLine) {
        String[] data = csvLine.split(";");
        if (data.length >= 7) {
            Antecedant ant = new Antecedant();
            ant.id = data[0];
            ant.type = data[1];
            ant.description = data[2];
            ant.dateDecouverte = parseDate(data[3]);
            ant.niveauGravite = Integer.parseInt(data[4]);
            ant.commentaires = data[5];
            ant.statut = data[6];
            return ant;
        }
        return null;
    }

    // ✅ GETTERS ET SETTERS COMPLETS
    public String getId() { return id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDecouverte() { return dateDecouverte; }
    public void setDateDecouverte(LocalDate dateDecouverte) {
        this.dateDecouverte = dateDecouverte;
    }
    public void setDateDecouverte(String dateStr) {
        this.dateDecouverte = parseDate(dateStr);
    }

    public int getNiveauGravite() { return niveauGravite; }
    public void setNiveauGravite(int niveauGravite) {
        this.niveauGravite = validerNiveauGravite(niveauGravite);
    }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getStatut() { return statut; }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    // ✅ MÉTHODE TOSTRING AMÉLIORÉE
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Gravité: %d/5)",
                id, getTypeFormate(), description, niveauGravite);
    }

    // ✅ MÉTHODE POUR TESTER LA CLASSE
    public static void main(String[] args) {
        System.out.println("=== TEST CLASSE ANTÉCÉDENT ===");

        // Test création manuelle
        Antecedant test1 = new Antecedant("ALLERGIE", "Allergie aux pénicillines",
                LocalDate.of(2020, 5, 15), 4);
        test1.afficherDetails();

        // Test création interactive
        System.out.println("\n=== CRÉATION INTERACTIVE ===");
        Antecedant test2 = Antecedant.creerAntecedantInteractif();
        test2.afficherDetails();

        // Test modification
        test2.setNiveauGravite(2);
        test2.setCommentaires("Sous contrôle médical");
        test2.mettreAJourStatut("RESOLU");
        test2.afficherDetails();
    }
}