package modele.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author bts
 * @version 1.0
 */
public class FabriqueJdbc {

    /**
     * instancier le singleton de connexion Jdbc en fonction d'un fichier de paramètres
     *
     * @param ficPropertiesJdbc nom du fichier de properties
     * @throws java.io.FileNotFoundException
     */
    public static void creer(String ficPropertiesJdbc) throws FileNotFoundException, IOException {
        Properties propertiesJdbc; // objet de propriétés (paramètres de l'appplication) pour Jdbc
        FileInputStream input; // flux de lecture des properties
        // Chargement des paramètres du fichier de properties
        propertiesJdbc = new Properties();
        input = new FileInputStream(ficPropertiesJdbc);
        propertiesJdbc.load(input);
        input.close();

        // Ouvrir une connexion JDBC vers la base de données visée :
        // paramétrer la connexion à partir des propriétés lues
        Jdbc.creer(propertiesJdbc.getProperty("pilote"),
                propertiesJdbc.getProperty("protocole"),
                propertiesJdbc.getProperty("url"),
                propertiesJdbc.getProperty("base"),
                propertiesJdbc.getProperty("utilisateur"),
                propertiesJdbc.getProperty("mdp"));
    }
    
}
