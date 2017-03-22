package modele.metier;

/**
*
* @author bts
*/
public class Labo {
    
    private String code;
    private String nom;
    private String chefVente;

    public Labo(String code, String nom, String chefVente) {
        this.code = code;
        this.nom = nom;
        this.chefVente = chefVente;
    }
    
    public Labo() {
        this.code = "";
        this.nom = "";
        this.chefVente = "";
    }
    
      public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChefVente() {
        return chefVente;
    }

    public void setChefVente(String chefVente) {
        this.chefVente = chefVente;
    }

    @Override
    public String toString() {
        return nom ;
    }
    
    
    
}