package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.dao.DishCategoryDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishCategoryTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.DishCategoryService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.util.validation.DishValidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link DishCategoryService} interface implementation.
 *
 * @author k1ly
 */
public class DishCategoryServiceImpl implements DishCategoryService {

    @Override
    public int addDishCategory(DishCategory dishCategory) throws ServiceException {
        int status = -1;
        DishValidator dishValidator = DishValidator.getInstance();
        if (dishValidator.isNameValid(dishCategory.getName())) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            DishCategoryDAO dishCategoryDAO = daoProvider.getDishCategoryRepository();
            try {
                status = dishCategoryDAO.addDishCategory(dishCategory) ? 1 : 0;
            } catch (DAOException e) {
                throw new ServiceException("Dish category adding error", e);
            }
        }
        return status;
    }

    @Override
    public List<DishCategory> browseDishCategories() throws ServiceException {
        List<DishCategory> dishCategoryList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishCategoryDAO dishCategoryDAO = daoProvider.getDishCategoryRepository();
        try {
            dishCategoryList = dishCategoryDAO.browseDishCategories(false, DishCategoryTable.ID.getColumnName(), true);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining dish categories error", e);
        }
        return dishCategoryList;
    }

    @Override
    public Optional<DishCategory> findDishCategoryById(int dishCategoryId) throws ServiceException {
        Optional<DishCategory> dishCategory;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishCategoryDAO dishCategoryDAO = daoProvider.getDishCategoryRepository();
        try {
            Map<String, Object> dishCategoryIdParameter = new LinkedHashMap<>();
            dishCategoryIdParameter.put(DishCategoryTable.ID.getColumnName(), dishCategoryId);
            List<DishCategory> dishCategoryList = dishCategoryDAO.findDishCategories(ParameterMap.of(dishCategoryIdParameter));
            dishCategory = dishCategoryList.size() == 1 ? Optional.of(dishCategoryList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Dish categories searching error", e);
        }
        return dishCategory;
    }

    @Override
    public int updateDishCategory(DishCategory dishCategory) throws ServiceException {
        int status = -1;
        DishValidator dishValidator = DishValidator.getInstance();
        if (dishValidator.isNameValid(dishCategory.getName())) {
            status = 0;
            if (dishCategory.getId() > 0) {
                DAOProvider daoProvider = DAOProvider.getInstance();
                DishCategoryDAO dishCategoryDAO = daoProvider.getDishCategoryRepository();
                try {
                    Map<String, Object> dishIdParameter = new LinkedHashMap<>();
                    dishIdParameter.put(DishCategoryTable.ID.getColumnName(), dishCategory.getId());
                    status = dishCategoryDAO.updateDishCategory(ParameterMap.of(dishIdParameter), dishCategory) ? 1 : 0;
                } catch (DAOException e) {
                    throw new ServiceException("Dish category updating error", e);
                }
            }
        }
        return status;
    }

    @Override
    public boolean deleteDishCategory(int dishCategoryId) throws ServiceException {
        boolean isDishCategoryDeleted;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishCategoryDAO dishCategoryDAO = daoProvider.getDishCategoryRepository();
        try {
            Map<String, Object> dishCategoryIdParameter = new LinkedHashMap<>();
            dishCategoryIdParameter.put(DishCategoryTable.ID.getColumnName(), dishCategoryId);
            isDishCategoryDeleted = dishCategoryDAO.deleteDishCategory(ParameterMap.of(dishCategoryIdParameter));
        } catch (DAOException e) {
            throw new ServiceException("Dish category deleting error", e);
        }
        return isDishCategoryDeleted;
    }
}
