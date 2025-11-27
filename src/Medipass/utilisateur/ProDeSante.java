package Medipass.utilisateur;

public abstract class ProDeSante extends Utilisateur {
    protected String specialite;
    protected String numeroRPPS; // Numéro RPPS pour les professionnels de santé
    protected boolean disponibilite;

    // ✅ CONSTRUCTEURS
    public ProDeSante(String id, String nom, String prenom, String email,
                      String numeroTelephone, String dateNaissance,
                      String adresse, String specialite, String motDePasse, String role) {
        super(id, nom, prenom, email, numeroTelephone, dateNaissance, adresse, motDePasse, role);
        this.specialite = specialite;
        this.disponibilite = true;
    }

    public ProDeSante() {
        super();
        this.disponibilite = true;
    }

    // ✅ MÉTHODES COMMUNES AUX PROFESSIONNELS
    public void changerDisponibilite(boolean disponible) {
        this.disponibilite = disponible;
        String statut = disponible ? "disponible" : "non disponible";
        System.out.println("✅ " + this.prenom + " " + this.nom + " est maintenant " + statut);
    }

    public void afficherDisponibilite() {
        String statut = this.disponibilite ? "🟢 Disponible" : "🔴 Non disponible";
        System.out.println("Statut: " + statut);
    }

    // ✅ MÉTHODE ABSTRAITE POUR CHAQUE TYPE DE PROFESSIONNEL
    public abstract void afficherMenuSpecifique();

    // ✅ GETTERS ET SETTERS
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getNumeroRPPS() { return numeroRPPS; }
    public void setNumeroRPPS(String numeroRPPS) { this.numeroRPPS = numeroRPPS; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }
}