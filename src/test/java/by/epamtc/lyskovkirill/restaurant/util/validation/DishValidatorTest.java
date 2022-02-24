package by.epamtc.lyskovkirill.restaurant.util.validation;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DishValidatorTest extends AbstractValidator {
    private DishValidator dishValidator;

    @BeforeEach
    void init() {
        dishValidator = DishValidator.getInstance();
    }

    @Test
    void testCorrectNameShouldBeValid() {
        String name = "Valid Dish Name";
        boolean isNameValid = dishValidator.isNameValid(name);
        assertTrue(isNameValid);
    }

    @Test
    void testNullNameShouldBeInvalid() {
        boolean isNameValid = dishValidator.isNameValid(null);
        assertFalse(isNameValid);
    }

    @Test
    void testEmptyNameShouldBeInvalid() {
        String name = "";
        boolean isNameValid = dishValidator.isNameValid(name);
        assertFalse(isNameValid);
    }

    @Test
    void testNameWithNumbersAndSpecialSymbolsShouldBeInvalid() {
        String name = "20q*3e#h7y73n%d2^41";
        boolean isNameValid = dishValidator.isNameValid(name);
        assertFalse(isNameValid);
    }

    @Test
    void testCorrectWeightShouldBeValid() {
        Integer weight = 300;
        boolean isWeightValid = dishValidator.isWeightValid(weight);
        assertTrue(isWeightValid);
    }

    @Test
    void testNullWeightShouldBeInvalid() {
        boolean isWeightValid = dishValidator.isWeightValid(null);
        assertFalse(isWeightValid);
    }

    @Test
    void testNegativeWeightShouldBeInvalid() {
        Integer weight = -247;
        boolean isWeightValid = dishValidator.isWeightValid(weight);
        assertFalse(isWeightValid);
    }

    @Test
    void testCorrectPriceShouldBeValid() {
        Double price = 185.0;
        boolean isPriceValid = dishValidator.isPriceValid(price);
        assertTrue(isPriceValid);
    }

    @Test
    void testNullPriceShouldBeInvalid() {
        boolean isPriceValid = dishValidator.isPriceValid(null);
        assertFalse(isPriceValid);
    }

    @Test
    void testNegativePriceShouldBeInvalid() {
        Double price = -361.25;
        boolean isPriceValid = dishValidator.isPriceValid(price);
        assertFalse(isPriceValid);
    }

    @Test
    void testCorrectDiscountShouldBeValid() {
        Integer discount = 50;
        boolean isDiscountValid = dishValidator.isDiscountValid(discount);
        assertTrue(isDiscountValid);
    }

    @Test
    void testNullDiscountShouldBeInvalid() {
        boolean isDiscountValid = dishValidator.isDiscountValid(null);
        assertFalse(isDiscountValid);
    }

    @Test
    void testNegativeDiscountShouldBeInvalid() {
        Integer discount = -105;
        boolean isDiscountValid = dishValidator.isDiscountValid(discount);
        assertFalse(isDiscountValid);
    }

    @Test
    void testDiscountGreaterThanOneHundredShouldBeInvalid() {
        Integer discount = 429;
        boolean isDiscountValid = dishValidator.isDiscountValid(discount);
        assertFalse(isDiscountValid);
    }

    @Test
    void testCorrectDishEntryShouldBeValid() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setWeight(200);
        dish.setPrice(80.0);
        dish.setDiscount(33);

        boolean isDishValid = dishValidator.isDishValid(dish);
        assertTrue(isDishValid);
    }

    @Test
    void testNullDishEntryShouldBeInvalid() {
        boolean isDishValid = dishValidator.isDishValid(null);
        assertFalse(isDishValid);
    }

    @Test
    void testDishEntryWithCorrectNotNullFieldShouldBeValid() {
        Dish dish = new Dish();
        dish.setName("Dish Name");

        boolean isDishValid = dishValidator.isDishValid(dish);
        assertTrue(isDishValid);
    }

    @Test
    void testDishEntryWithAnyIncorrectFieldShouldBeInvalid() {
        Dish dish = new Dish();
        dish.setName("invalid&name123");
        dish.setPrice(10.0);

        boolean isDishValid = dishValidator.isDishValid(dish);
        assertFalse(isDishValid);
    }
}