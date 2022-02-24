package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.DishDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishTable;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link DishDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLDishDAO extends AbstractSQLDAO<Dish> implements DishDAO {
    private static final String TABLE_NAME = "dishes";

    public SQLDishDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addDish(Dish dish) throws DAOException {
        boolean isDishAdded = false;
        if (dish != null) {
            try {
                Integer generatedId = insertEntity(takeFields(dish));
                dish.setId(generatedId);
                isDishAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Dish inserting error", exception);
            }
        }
        return isDishAdded;
    }

    @Override
    public List<Dish> findDishes(ParameterMap parameters) throws DAOException {
        List<Dish> dishList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(Dish.class, sql)) {
                dishList.add((Dish) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Dish selecting error", exception);
        }
        return dishList;
    }

    @Override
    public List<Dish> findDishes(ParameterMap parameters, boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException {
        List<Dish> dishList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(Dish.class, makePaginationQuery(sql, firstRow, rowCount))) {
                dishList.add((Dish) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Dish selecting error", exception);
        }
        return dishList;
    }

    @Override
    public List<Dish> browseDishes(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException {
        List<Dish> dishList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(Dish.class, makePaginationQuery(sql, firstRow, rowCount))) {
                dishList.add((Dish) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Dish selecting error", exception);
        }
        return dishList;
    }

    @Override
    public boolean updateDish(ParameterMap parameters, Dish dish) throws DAOException {
        boolean isDishUpdated = false;
        if (dish != null) {
            try {
                isDishUpdated = updateEntity(takeFields(dish), parameters) == 1;
            } catch (SQLException exception) {
                throw new DAOException("Dish updating error", exception);
            }
        }
        return isDishUpdated;
    }

    @Override
    public boolean deleteDish(ParameterMap deleteId) throws DAOException {
        boolean isDishDeleted;
        try {
            isDishDeleted = softDeleteEntity(deleteId) == 1;
        } catch (SQLException exception) {
            throw new DAOException("Dish soft deleting error", exception);
        }
        return isDishDeleted;
    }

    @Override
    public int countDishes(ParameterMap parameters, boolean includeDeleted) throws DAOException {
        try {
            return countTableRows(makeQueryCondition(parameters.getParameters(), includeDeleted));
        } catch (SQLException exception) {
            throw new DAOException("Dish counting error", exception);
        }
    }

    @Override
    public ParameterMap takeFields(Dish dish) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (dish.getName() != null)
            fields.put(DishTable.NAME.getColumnName(), dish.getName());
        if (dish.getComposition() != null)
            fields.put(DishTable.COMPOSITION.getColumnName(), dish.getComposition());
        if (dish.getImage() != null)
            fields.put(DishTable.IMAGE.getColumnName(), dish.getImage());
        if (dish.getWeight() != null)
            fields.put(DishTable.WEIGHT.getColumnName(), dish.getWeight());
        if (dish.getPrice() != null)
            fields.put(DishTable.PRICE.getColumnName(), dish.getPrice());
        if (dish.getDiscount() != null)
            fields.put(DishTable.DISCOUNT.getColumnName(), dish.getDiscount());
        if (dish.getCategory() != null && dish.getCategory().getId() > 0)
            fields.put(DishTable.DISH_CATEGORY_ID.getColumnName(), dish.getCategory().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return DishTable.ID.getColumnName()
                + ", " + DishTable.NAME.getColumnName()
                + ", " + DishTable.COMPOSITION.getColumnName()
                + ", " + DishTable.IMAGE.getColumnName()
                + ", " + DishTable.WEIGHT.getColumnName()
                + ", " + DishTable.PRICE.getColumnName()
                + ", " + DishTable.DISCOUNT.getColumnName()
                + ", " + DishTable.DISH_CATEGORY_ID.getColumnName();
    }
}
