package modele.dao;

/**
 *
 * @author bts
 */
public class DaoException extends Exception {

    /**
     * Creates a new instance of <code>DaoException</code> without detail message.
     */
    public DaoException() {
    }

    /**
     * Constructs an instance of <code>DaoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DaoException(String msg) {
        super(msg);
    }
}
