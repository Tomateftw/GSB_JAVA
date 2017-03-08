package modele.dao;

import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;

/**
 * Classe DAO pour la classe Secteur
 *
 * @version 1.0
 * @author bts
 */
public class DaoSecteur implements DaoInterface<Secteur, Integer> {

    /**
     * Non implémenté
     * @param unSecteur
     */
    @Override
    public int create(Secteur unSecteur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Lire un enregistrement d'après son identifiant
     *
     * @param idSecteur métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws modele.dao.DaoException
     */
    @Override
    public Secteur getOne(Integer idSecteur) throws DaoException {
        Secteur result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM SECTEUR WHERE SEC_CODE=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idSecteur);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSecteur::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }
    public Secteur getOne(String idSecteur) throws DaoException {
        Secteur result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM SECTEUR WHERE SEC_CODE=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1, idSecteur);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSecteur::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'secteurs de la table
     * SECTEUR
     * @throws modele.dao.DaoException
     */
    @Override
    public ArrayList<Secteur> getAll() throws DaoException {
        ArrayList<Secteur> result = new ArrayList<Secteur>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM SECTEUR";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Secteur unSecteur = chargerUnEnregistrement(rs);
                result.add(unSecteur);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSecteur::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Secteur objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementSecteur Instancie un objet secteur avec les
     * valeurs lues dans la base de données La jointure avec la table PRESENCE
     * n'est pas effectuée
     *
     * @param rs enregistrement de la table Secteur courant
     * @return un objet Secteur, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    private Secteur chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Secteur secteur = new Secteur(null,null);
            secteur.setCode(rs.getString("SEC_CODE"));
            secteur.setLibelle(rs.getString("SEC_LIBELLE"));
            
            return secteur;
        } catch (SQLException ex) {
            throw new DaoException("DaoSecteur - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
