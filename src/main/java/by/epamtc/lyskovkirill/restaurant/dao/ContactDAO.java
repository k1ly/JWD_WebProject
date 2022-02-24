package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Contact;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;

import java.util.Optional;

/**
 * DAO interface that encapsulates {@link Contact} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface ContactDAO {

    /**
     * Tries to add the specified {@link Contact} into data source.
     * Returns boolean adding process status value.
     *
     * @param contact the {@link Contact} which should be added.
     * @return false if {@link Contact} was not added,
     * or true if {@link Contact} was successfully added.
     * @throws DAOException if an error occurred during adding the contact.
     */
    boolean addContact(Contact contact) throws DAOException;

    /**
     * Tries to find the {@link Contact} entry from data source.
     *
     * @return the {@link Optional#empty()} if the contact was not found,
     * or {@link Contact} entry if contact exists.
     * @throws DAOException if an error occurred during retrieving contact.
     */
    Optional<Contact> findContact() throws DAOException;
}
