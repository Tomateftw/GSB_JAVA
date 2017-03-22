package modele.metier;

/**
 *
 * @author bts
 */
public class Visiteur {
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String dateEmbauche;
    private Secteur secteur;
    private Labo labo;

    public Visiteur(String matricule, String nom, String prenom, String adresse, String cp, String ville, String dateEmbauche, Secteur secteur, Labo labo) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;
        this.secteur = secteur;
        this.labo = labo;
    }

    @Override
    public String toString() {
        return  nom + " " + prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Labo getLabo() {
        return labo;
    }

    public void setLabo(Labo labo) {
        this.labo = labo;
    }

}