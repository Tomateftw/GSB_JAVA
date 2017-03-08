package modele.jdbc;

import java.sql.*;
import java.util.List;

public interface JdbcInterface {
  
    /**
     * Connexion à la base de données
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void connecter() throws ClassNotFoundException, SQLException   ;

    /**
     * Déconnexion à la base de données
     * @throws SQLException 
     */
    public void deconnecter() throws SQLException   ;

    /**
     * Démarrer une transaction JDBC sur la base de données
     * @throws SQLException 
     */
    public void debuterTransaction()  throws SQLException  ;

    /**
     * Fin de la transaction en validant les modifications depuis 
     * le début de la transaction
     * @throws SQLException 
     */
    public void validerTransaction()  throws SQLException  ;

    /**
     * Fin de la transaction en annulant les modifications depuis 
     * le début de la transaction
     * @throws SQLException 
     */
    public void annulerTransaction()  throws SQLException ;

    /**
     * Exécution d'une requête SQL SELECT non paramétrée
     * @param requete texte de la requête
     * @return jeu d'enregistrements résultant
     * @throws SQLException 
     */
    public ResultSet consulter(String requete) throws SQLException ;

    
    /**
     * Exécution d'une requête SQL SELECT paramétrée
     * @param requete texte de la requête paramétrée
     * @param param liste des valeurs des paramètres
     * @return jeu d'enregistrements résultant
     * @throws SQLException 
     */
    public ResultSet consulter(String requete, List param) throws SQLException ;

    /**
     * Exécution d'une requête SQL UPDATE, INSERT ou DELETE
     * @param requete texte de la requête non paramétrée
     * @return nombre d'enregistrements mis à jour
     * @throws SQLException 
     */
    public int mettreAJour(String requete) throws SQLException ;
    
     /**
     * Exécution d'une requête SQL UPDATE, INSERT ou DELETE paramétrée
     * @param requete texte de la requête paramétrée
     * @param param liste des valeurs des paramètres
     * @return nombre d'enregistrements mis à jour
     * @throws SQLException 
     */
    public int mettreAJour(String requete, List param) throws SQLException;

     /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef générée automatiquement
     * utile par exemple pour insérer simultanément dans deux tables liées par une 
     * référence sur cette clef
     * @param requete : texte de la requête SQL non paramétrée
     * @return Resultset contenant le dernier ID généré
     * @throws java.sql.SQLException
     *
     */
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete) throws SQLException;

     /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef générée automatiquement
     * utile par exemple pour insérer simultanément dans deux tables liées par une 
     * référence sur cette clef
     * @param requete : texte de la requête SQL paramétrée
     * @param param : liste des valeurs des paramètres
     * @return Resultset contenant le dernier ID généré
     * @throws java.sql.SQLException
     *
     */
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete, List param) throws SQLException;

	/** Fournit la liste des noms de champs du résultat d'une requête 
	*@param rs résultat d'une requête sur la BD associée à la connexion
	*@return List : liste des noms de colonnes d'après les méta  données de la BD
     * @throws java.sql.SQLException
	*/
    public  List getNomColonnes (ResultSet rs )  throws SQLException;
    
}