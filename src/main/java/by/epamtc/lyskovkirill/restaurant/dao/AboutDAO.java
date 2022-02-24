package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;

/**
 * DAO interface that encapsulates about text processing using data source.
 *
 * @author k1ly
 */
public interface AboutDAO {

    /**
     * Tries to add the specified <b>about text</b> into data source.
     * Returns boolean adding process status value.
     *
     * @param about the about text which should be added.
     * @return false if about text was not added,
     * or true if about text was successfully added.
     * @throws DAOException if an error occurred during adding the about text.
     */
    boolean addAbout(String about) throws DAOException;

    /**
     * Tries to find an <b>about text</b> entry from data source.
     *
     * @return the about text entry.
     * @throws DAOException if an error occurred during retrieving the about text.
     */
    String findAbout() throws DAOException;
}
