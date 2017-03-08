package modele.dao;

import java.util.Collection;

/**
 * Modéle de classe DAO 
 *
 * @version 1.0
 * @author bts
 * @param <TMetier>
 * @param <TIdMetier>
 */
public interface DaoInterface<TMetier, TIdMetier> {

    /**
     * create
     *
     * @param objetMetier métier contenant les données nécessaires à l'ajout
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int create(TMetier objetMetier) throws Exception;

    /**
     * getOne
     *
     * @param idMetier métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws java.lang.Exception
     */
    public TMetier getOne(TIdMetier idMetier) throws Exception;

    /**
     * getAll
     *
     * @return collection de l'ensemble des objets métier disponibles; elle peut
     * étre vide
     * @throws java.lang.Exception
     */
    public Collection<TMetier> getAll() throws Exception;

//    /**
//     * retrieveMany
//     * @param objet contenant les critéres de recherche, null si aucun critére (retourner tous les objets)
//     * @return collection des objets métier trouvé; elle peut étre vide
//     */
//    public Collection<TMetier> retrieveMany(String criteres) throws Exception;
    
    
    /**
     * update
     *
     * @param idMetier métier de l'objet à modifier
     * @param objetMetier métier modifié
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int update(TIdMetier idMetier, TMetier objetMetier) throws Exception;

    /**
     * delete
     *
     * @param idMetier métier de l'objet à détruire
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int delete(TIdMetier idMetier) throws Exception;
}
