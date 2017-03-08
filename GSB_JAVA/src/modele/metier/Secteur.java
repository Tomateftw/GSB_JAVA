package modele.metier;

/**
 *
 * @author bts
 */
public class Secteur {
    private String code;
    private String libelle ;

    public Secteur(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}
