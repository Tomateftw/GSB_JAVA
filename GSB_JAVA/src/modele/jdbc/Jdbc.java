package modele.jdbc;

import java.sql.*;
import java.util.*;

/**
 * Singleton fournit un objet de connexion JDBC
 *
 * @author bts
 * @version 1.0
 */
public class Jdbc implements JdbcInterface {

    // Instance du singleton Jdbc
    private static Jdbc singleton = null;
    
    // Paramètre de la connexion
    private String piloteJdbc = "";
    private String protocoleJdbc = "";
    private String serveurBd = "";
    private String nomBd = "";
    private String loginSgbd = "";
    private String mdpSgbd = "";
    
    // Connexion
    private Connection connexion = null; // java.sql.Connection
    
    private Jdbc() {
    }

    /**
     * @param pilote : classe du pilote Jdbc
     * @param protocole : préfixe l'URL du serveur ; dépend du type de SGBD
     * @param serveur : adresse du serveur + port (fini par un /, sauf pour
     * Oracle ; BD pour embarquée : chemin accès répertoire )
     * @param base : nom de la BD ou du DSN pour ODBC
     * @param login : utilisateur autorisé du SGBD (ou schéma Oracle)
     * @param mdp : son mot de passe
     */
    private Jdbc(String pilote, String protocole, String serveur, String base, String login, String mdp) {
        this.piloteJdbc = pilote;
        this.protocoleJdbc = protocole;
        this.serveurBd = serveur;
        this.nomBd = base;
        this.loginSgbd = login;
        this.mdpSgbd = mdp;
    }

    public static Jdbc creer(String pilote, String protocole, String serveur, String base, String login, String mdp) {
        if (singleton == null) {
            singleton = new Jdbc(pilote, protocole, serveur, base, login, mdp);
        }
        return singleton;
    }

    public static Jdbc getInstance() {
        return singleton;
    }

    @Override
    public void connecter() throws ClassNotFoundException, SQLException {
        Class.forName(this.getPiloteJdbc());
        setConnexion(DriverManager.getConnection(this.getProtocoleJdbc() + this.getServeurBd() + this.getNomBd(), this.getLoginSgbd(), this.getMdpSgbd()));
        getConnexion().setAutoCommit(true);
    }

    @Override
    public void deconnecter() throws SQLException {
        getConnexion().close();
    }

    @Override
    public void debuterTransaction() throws SQLException {
        getConnexion().setAutoCommit(false);
    }

    @Override
    public void validerTransaction() throws SQLException {
        getConnexion().commit();
        getConnexion().setAutoCommit(true);
    }

    @Override
    public void annulerTransaction() throws SQLException {
        getConnexion().rollback();
    }

    @Override
    public ResultSet consulter(String requete) throws SQLException {
        ResultSet rs;
        Statement st = getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(requete);
        return rs;
    }

    @Override
    public ResultSet consulter(String requete, List param) throws SQLException {
        ResultSet rs;
        int index = 0;
        PreparedStatement ps = this.getConnexion().prepareStatement(requete, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        for (Object o : param) {
            ps.setObject(++index, o);
        }
        rs = ps.executeQuery();
        return rs;
    }

    @Override
    /**
     * mettreAJour() : version avec Statement
     *
     * @param requete requête SQL
     * @return nbre d'enregistrements modifiés
     * @throws java.sql.SQLException
     */
    public int mettreAJour(String requete) throws SQLException {
        int nb;
        Statement st = getConnexion().createStatement();
        nb = st.executeUpdate(requete);
        return nb;
    }

    /**
     * mettreAJour() : version avec PreparedStatement
     *
     * @param requete requête SQL paramétrée
     * @param param liste des valeurs des paramètres effectifs de la requête
     * @return nbre d'enregistrements modifiés
     * @throws java.sql.SQLException
     */
    @Override
    public int mettreAJour(String requete, List param) throws SQLException {
        int nb;
        int index = 0;
        PreparedStatement ps = this.getConnexion().prepareStatement(requete);
        for (Object o : param) {
            ps.setObject(++index, o);
        }
        nb = ps.executeUpdate();
        return nb;
    }

    /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef générée automatiquement
     * utile par exemple pour insérer simultanément dans deux tables liées par une 
     * référence sur cette clef
     * @param requete : texte de la requête SQL non paramétrée
     * @return Resultset : contenant le dernier ID généré
     * @throws java.sql.SQLException
     *
     */
    @Override
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete) throws SQLException {
        ResultSet rsGK; // ResultSet devant contenir le dernier ID généré ou vide
        int nb;
        Statement st = getConnexion().createStatement();
        nb = st.executeUpdate(requete, Statement.RETURN_GENERATED_KEYS);
        rsGK = st.getGeneratedKeys();
        return rsGK;
    }

     /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef générée automatiquement
     * utile par exemple pour insérer simultanément dans deux tables liées par une 
     * référence sur cette clef
     * @param requete : texte de la requête SQL paramétrée
     * @param param : liste des valeurs des paramètres
     * @return Resultset : contenant le dernier ID généré
     * @throws java.sql.SQLException
     *
     */
    @Override
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete, List param) throws SQLException {
        ResultSet rsGK; // ResultSet devant contenir le dernier ID généré ou vide
        int nb;
        int index = 0;
        PreparedStatement ps = this.getConnexion().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        for (Object o : param) {
            ps.setObject(++index, o);
        }
        nb = ps.executeUpdate();
        rsGK = ps.getGeneratedKeys();
        return rsGK;
    }

    /**
     * Fournit la liste des noms de champs du résultat d'une requête
     *
     * @param rs résultat d'une requête sur la BD associée à la
     * connexion
     * @return List : liste des noms de colonnes d'après les méta données de la
     * BD
     * @throws java.sql.SQLException
     */
    @Override
    public List getNomColonnes(ResultSet rs) throws SQLException {
        ArrayList listeResultat = new ArrayList();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            listeResultat.add(rsmd.getColumnName(i + 1));
        }
        return listeResultat;
    }

    public static java.sql.Date utilDateToSqlDate(java.util.Date uneDate) {
        return (new java.sql.Date(uneDate.getTime()));
    }

    /**
     * ***************************************
     */
    /*          ACCESSEURS & MUTATEURS                  */
    /**
     * ***************************************
     * @return the piloteJdbc
     */
    public String getPiloteJdbc() {
        return piloteJdbc;
    }

    public void setPiloteJdbc(String piloteJdbc) {
        this.piloteJdbc = piloteJdbc;
    }

    /**
     * @return the protocoleJdbc
     */
    public String getProtocoleJdbc() {
        return protocoleJdbc;
    }

    /**
     * @param protocoleJdbc the protocoleJdbc to set
     */
    public void setProtocoleJdbc(String protocoleJdbc) {
        this.protocoleJdbc = protocoleJdbc;
    }

    public String getServeurBd() {
        return serveurBd;
    }

    public void setServeurBd(String serveurBd) {
        this.serveurBd = serveurBd;
    }

    public String getNomBd() {
        return nomBd;
    }

    public void setNomBd(String nomBd) {
        this.nomBd = nomBd;
    }

    public String getLoginSgbd() {
        return loginSgbd;
    }

    public void setLoginSgbd(String loginSgbd) {
        this.loginSgbd = loginSgbd;
    }

    public String getMdpSgbd() {
        return mdpSgbd;
    }

    public void setMdpSgbd(String mdpSgbd) {
        this.mdpSgbd = mdpSgbd;
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }

}