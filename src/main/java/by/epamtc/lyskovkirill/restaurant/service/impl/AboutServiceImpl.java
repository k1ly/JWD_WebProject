package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.dao.AboutDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.service.AboutService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

/**
 * {@link AboutService} interface implementation.
 *
 * @author k1ly
 */
public class AboutServiceImpl implements AboutService {

    @Override
    public boolean addAbout(String about) throws ServiceException {
        boolean isAboutCreated;
        DAOProvider daoProvider = DAOProvider.getInstance();
        AboutDAO aboutDAO = daoProvider.getAboutRepository();
        try {
            isAboutCreated = aboutDAO.addAbout(about);
        } catch (DAOException e) {
            throw new ServiceException("About text adding error", e);
        }
        return isAboutCreated;
    }

    @Override
    public String findAbout() throws ServiceException {
        String about;
        DAOProvider daoProvider = DAOProvider.getInstance();
        AboutDAO aboutDAO = daoProvider.getAboutRepository();
        try {
            about = aboutDAO.findAbout();
        } catch (DAOException e) {
            throw new ServiceException("Obtaining about text error", e);
        }
        return about;
    }
}
