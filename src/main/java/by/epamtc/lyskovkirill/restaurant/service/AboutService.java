package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

/**
 * Service interface that encapsulates about text processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface AboutService {

    /**
     * Tries to add the specified <b>about text</b> using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param about the about text which should be added.
     * @return false if about text was not added,
     * or true if about text was successfully added.
     * @throws ServiceException if an error occurred during about text adding process.
     */
    boolean addAbout(String about) throws ServiceException;

    /**
     * Tries to find an <b>about text</b> entry using the DAO method.
     *
     * @return the about text entry.
     * @throws ServiceException if an error occurred during about text obtaining process.
     */
    String findAbout() throws ServiceException;
}
