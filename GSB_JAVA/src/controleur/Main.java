package controleur;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modele.jdbc.FabriqueJdbc;
import modele.jdbc.Jdbc;

/**
 * GSB VISITEUR
 *
 * @version 1.0
 * @author bts 
 * Objectif : 
 * - exemple de dynamique Vue/Controleur avec controleur principal
 * - exemple de pattern Dao
 * - exemple de singleton de connexion à un SGBD
 * - exemple de paramétrage utilisant un fichier de "properties"
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        String ficPropertiesJdbc = "gsbJdbc.properties"; // nom du fichier de properties
        CtrlPrincipal ctrlPrincipal;                     // référence vers le contrôleur principal

        // si au moins un paramètre a été passé sur la ligne de commandes
        // le premier est le nom du fichier contenant les propriétés de connexion JDBC
        // (Lancement en ligne de commande)
        if (args.length > 0) {
            ficPropertiesJdbc = args[0];
        }
        
        // 1- instancier le singleton de connexion Jdbc en fonction d'un fichier de paramètres
        // La classe Jdbc étant un singleton, la connexion s'obtient ainsi :
        // Jdbc.getInstance().getConnexion()
        // La connexion est utilisée dans les classes Dao
        try {
            FabriqueJdbc.creer(ficPropertiesJdbc);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - Fichier de propriétés JDBC introuvable", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - Erreur lors du chargement du fichier de propriétés JDBC", JOptionPane.ERROR_MESSAGE);
        }

       // 2 - ouvrir la connexion
        try {
            Jdbc.getInstance().connecter();
        } catch (ClassNotFoundException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - connexion à la BDD - pilote JDBC non trouvé", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - connexion à la BDD", JOptionPane.ERROR_MESSAGE);
        }
        
        // Pour lancer l'application, instancier le contrôleur principal
        ctrlPrincipal = new CtrlPrincipal();
        ctrlPrincipal.action();
    }
}
