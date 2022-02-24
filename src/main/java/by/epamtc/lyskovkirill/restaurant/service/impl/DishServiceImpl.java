package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.dao.DishDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishTable;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.util.validation.DishValidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link DishService} interface implementation.
 *
 * @author k1ly
 */
public class DishServiceImpl implements DishService {

    @Override
    public int addDish(Dish dish) throws ServiceException {
        int status = -1;
        DishValidator dishValidator = DishValidator.getInstance();
        if (dishValidator.isDishValid(dish)) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            DishDAO dishDAO = daoProvider.getDishRepository();
            try {
                status = dishDAO.addDish(dish) ? 1 : 0;
            } catch (DAOException e) {
                throw new ServiceException("Dish adding error", e);
            }
        }
        return status;
    }

    @Override
    public List<Dish> browseDishes(String orderAttr, boolean asc, int count, int page) throws ServiceException {
        List<Dish> dishList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            String dishAttribute = null;
            for (var column : DishTable.values()) {
                if (column.getColumnName().equals(orderAttr)) {
                    dishAttribute = column.getColumnName();
                    break;
                }
            }
            int firstRow = (page - 1) * count + 1;
            dishList = dishDAO.browseDishes(false, dishAttribute, asc, firstRow, count);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining dishes error", e);
        }
        return dishList;
    }

    @Override
    public List<Dish> findDishesByCategory(int categoryId, String orderAttr, boolean asc, int count, int page) throws ServiceException {
        List<Dish> dishList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            Map<String, Object> dishCategoryParameter = new LinkedHashMap<>();
            dishCategoryParameter.put(DishTable.DISH_CATEGORY_ID.getColumnName(), categoryId);
            String dishAttribute = null;
            for (var column : DishTable.values()) {
                if (column.getColumnName().equals(orderAttr)) {
                    dishAttribute = column.getColumnName();
                    break;
                }
            }
            int firstRow = (page - 1) * count + 1;
            dishList = dishDAO.findDishes(ParameterMap.of(dishCategoryParameter), false,
                    dishAttribute, asc, firstRow, count);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining dishes error", e);
        }
        return dishList;
    }

    @Override
    public Optional<Dish> findDishById(int dishId) throws ServiceException {
        Optional<Dish> dish;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            Map<String, Object> dishIdParameter = new LinkedHashMap<>();
            dishIdParameter.put(DishTable.ID.getColumnName(), dishId);
            List<Dish> dishList = dishDAO.findDishes(ParameterMap.of(dishIdParameter));
            dish = dishList.size() == 1 ? Optional.of(dishList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Dish searching error", e);
        }
        return dish;
    }

    @Override
    public int updateDish(Dish dish) throws ServiceException {
        int status = -1;
        DishValidator dishValidator = DishValidator.getInstance();
        if (dishValidator.isDishValid(dish)) {
            status = 0;
            if (dish.getId() > 0) {
                DAOProvider daoProvider = DAOProvider.getInstance();
                DishDAO dishDAO = daoProvider.getDishRepository();
                try {
                    Map<String, Object> dishIdParameter = new LinkedHashMap<>();
                    dishIdParameter.put(DishTable.ID.getColumnName(), dish.getId());
                    status = dishDAO.updateDish(ParameterMap.of(dishIdParameter), dish) ? 1 : 0;
                } catch (DAOException e) {
                    throw new ServiceException("Dish updating error", e);
                }
            }
        }
        return status;
    }

    @Override
    public boolean deleteDish(int dishId) throws ServiceException {
        boolean isDishDeleted;
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            Map<String, Object> dishIdParameter = new LinkedHashMap<>();
            dishIdParameter.put(DishTable.ID.getColumnName(), dishId);
            isDishDeleted = dishDAO.deleteDish(ParameterMap.of(dishIdParameter));
        } catch (DAOException e) {
            throw new ServiceException("Dish deleting error", e);
        }
        return isDishDeleted;
    }

    @Override
    public int countDishes() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            return dishDAO.countDishes(new ParameterMap(), false);
        } catch (DAOException e) {
            throw new ServiceException("Dish counting error", e);
        }
    }

    @Override
    public int countDishes(int categoryId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishRepository();
        try {
            Map<String, Object> dishParameters = new LinkedHashMap<>();
            dishParameters.put(DishTable.DISH_CATEGORY_ID.getColumnName(), categoryId);
            return dishDAO.countDishes(ParameterMap.of(dishParameters), false);
        } catch (DAOException e) {
            throw new ServiceException("Dish counting error", e);
        }
    }
}
