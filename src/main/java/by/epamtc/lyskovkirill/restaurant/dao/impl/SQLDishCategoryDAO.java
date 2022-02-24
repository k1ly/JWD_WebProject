package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.DishCategoryDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishCategoryTable;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link DishCategoryDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLDishCategoryDAO extends AbstractSQLDAO<DishCategory> implements DishCategoryDAO {
    private static final String TABLE_NAME = "dish_categories";

    public SQLDishCategoryDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addDishCategory(DishCategory dishCategory) throws DAOException {
        boolean isDishCategoryAdded = false;
        if (dishCategory != null) {
            try {
                Integer generatedId = insertEntity(takeFields(dishCategory));
                dishCategory.setId(generatedId);
                isDishCategoryAdded = generatedId != null;
            } catch (SQLException exception) {
                throw new DAOException("Dish category inserting error", exception);
            }
        }
        return isDishCategoryAdded;
    }

    @Override
    public List<DishCategory> findDishCategories(ParameterMap parameters) throws DAOException {
        List<DishCategory> dishCategoryList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(DishCategory.class, sql)) {
                dishCategoryList.add((DishCategory) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Dish category selecting error", exception);
        }
        return dishCategoryList;
    }

    @Override
    public List<DishCategory> browseDishCategories(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException {
        List<DishCategory> dishCategoryList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(DishCategory.class, sql)) {
                dishCategoryList.add((DishCategory) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Dish category selecting error", exception);
        }
        return dishCategoryList;
    }

    @Override
    public boolean updateDishCategory(ParameterMap parameters, DishCategory dishCategory) throws DAOException {
        boolean isDishCategoryUpdated = false;
        if (dishCategory != null) {
            try {
                isDishCategoryUpdated = updateEntity(takeFields(dishCategory), parameters) == 1;
            } catch (SQLException exception) {
                throw new DAOException("Dish category updating error", exception);
            }
        }
        return isDishCategoryUpdated;
    }

    @Override
    public boolean deleteDishCategory(ParameterMap deleteId) throws DAOException {
        boolean isDishCategoryDeleted;
        try {
            isDishCategoryDeleted = softDeleteEntity(deleteId) == 1;
        } catch (SQLException exception) {
            throw new DAOException("Dish category soft deleting error", exception);
        }
        return isDishCategoryDeleted;
    }

    @Override
    public ParameterMap takeFields(DishCategory dishCategory) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (dishCategory.getName() != null)
            fields.put(DishCategoryTable.CATEGORY_NAME.getColumnName(), dishCategory.getName());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return DishCategoryTable.ID.getColumnName()
                + ", " + DishCategoryTable.CATEGORY_NAME.getColumnName();
    }
}
