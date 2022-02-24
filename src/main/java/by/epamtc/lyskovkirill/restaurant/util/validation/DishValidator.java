package by.epamtc.lyskovkirill.restaurant.util.validation;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;

/**
 * Class that is responsible for validating {@link Dish} entry.
 *
 * @author k1ly
 */
public class DishValidator extends AbstractValidator {
    private final static String NAME_PATTERN = "^(\\p{L})+([. '-](\\p{L})+)*$";
    private static final DishValidator instance = new DishValidator();

    private DishValidator() {
    }

    public static DishValidator getInstance() {
        return instance;
    }

    public boolean isNameValid(String name) {
        return name != null && isFieldValid(NAME_PATTERN, name);
    }

    public boolean isWeightValid(Integer weight) {
        return weight != null && weight >= 0;
    }

    public boolean isPriceValid(Double price) {
        return price != null && price >= 0;
    }

    public boolean isDiscountValid(Integer discount) {
        return discount != null && discount >= 0 && discount <= 100;
    }

    public boolean isDishValid(Dish dish) {
        if (dish == null)
            return false;
        boolean isDishValid = true;
        if (dish.getName() != null)
            isDishValid = isNameValid(dish.getName());
        if (dish.getWeight() != null)
            isDishValid &= isWeightValid(dish.getWeight());
        if (dish.getPrice() != null)
            isDishValid &= isPriceValid(dish.getPrice());
        if (dish.getDiscount() != null)
            isDishValid &= isDiscountValid(dish.getDiscount());
        return isDishValid;
    }
}