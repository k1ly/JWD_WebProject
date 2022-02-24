package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Contact;
import by.epamtc.lyskovkirill.restaurant.dao.ContactDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.service.ContactService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.Optional;

/**
 * {@link ContactService} interface implementation.
 *
 * @author k1ly
 */
public class ContactServiceImpl implements ContactService {

    @Override
    public boolean addContact(Contact contact) throws ServiceException {
        boolean isContactCreated;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContactDAO contactDAO = daoProvider.getContactRepository();
        try {
            isContactCreated = contactDAO.addContact(contact);
        } catch (DAOException e) {
            throw new ServiceException("Contact adding error", e);
        }
        return isContactCreated;
    }

    @Override
    public Optional<Contact> findContact() throws ServiceException {
        Optional<Contact> contact;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContactDAO contactDAO = daoProvider.getContactRepository();
        try {
            contact = contactDAO.findContact();
        } catch (DAOException e) {
            throw new ServiceException("Obtaining contacts error", e);
        }
        return contact;
    }
}
