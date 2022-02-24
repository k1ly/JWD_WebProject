package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Contact;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.Optional;

/**
 * Service interface that encapsulates {@link Contact} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface ContactService {

    /**
     * Tries to add the specified {@link Contact} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param contact the {@link Contact} which should be added.
     * @return false if {@link Contact} was not added,
     * or true if {@link Contact} was successfully added.
     * @throws ServiceException if an error occurred during contact adding process.
     */
    boolean addContact(Contact contact) throws ServiceException;

    /**
     * Tries to find a {@link Contact} entry using the DAO method.
     *
     * @return the {@link Optional#empty()} if the contact was not found,
     * or {@link Contact} entry if contact exists.
     * @throws ServiceException if an error occurred during contact obtaining process.
     */
    Optional<Contact> findContact() throws ServiceException;
}
