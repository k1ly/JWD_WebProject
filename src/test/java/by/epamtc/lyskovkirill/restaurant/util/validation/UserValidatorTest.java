package by.epamtc.lyskovkirill.restaurant.util.validation;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest extends AbstractValidator {
    private UserValidator userValidator;

    @BeforeEach
    void init() {
        userValidator = UserValidator.getInstance();
    }

    @Test
    void testCorrectLoginShouldBeValid() {
        String login = "Valid_Login123";
        boolean isLoginValid = userValidator.isLoginValid(login);
        assertTrue(isLoginValid);
    }

    @Test
    void testNullLoginShouldBeInvalid() {
        boolean isLoginValid = userValidator.isLoginValid(null);
        assertFalse(isLoginValid);
    }

    @Test
    void testEmptyLoginShouldBeInvalid() {
        String login = "";
        boolean isLoginValid = userValidator.isLoginValid(login);
        assertFalse(isLoginValid);
    }

    @Test
    void testLoginWithSpecialSymbolsShouldBeInvalid() {
        String login = "17#*${83!@24";
        boolean isLoginValid = userValidator.isLoginValid(login);
        assertFalse(isLoginValid);
    }

    @Test
    void testShortLoginShouldBeInvalid() {
        String login = "short";
        boolean isLoginValid = userValidator.isLoginValid(login);
        assertFalse(isLoginValid);
    }

    @Test
    void testLongLoginWithSpacesShouldBeInvalid() {
        String login = "very long login that contains spaces";
        boolean isLoginValid = userValidator.isLoginValid(login);
        assertFalse(isLoginValid);
    }

    @Test
    void testCorrectPasswordShouldBeValid() {
        String password = "Password1234";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertTrue(isPasswordValid);
    }

    @Test
    void testNullPasswordShouldBeInvalid() {
        boolean isPasswordValid = userValidator.isLoginValid(null);
        assertFalse(isPasswordValid);
    }

    @Test
    void testEmptyPasswordShouldBeInvalid() {
        String password = "";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertFalse(isPasswordValid);
    }

    @Test
    void testPasswordWithoutNumberAndCapitalShouldBeInvalid() {
        String password = "no_numbers_and_capital_letters";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertFalse(isPasswordValid);
    }

    @Test
    void testPasswordWithoutLettersShouldBeInvalid() {
        String password = "17#*${83!@24";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertFalse(isPasswordValid);
    }

    @Test
    void testShortPasswordShouldBeInvalid() {
        String password = "Short";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertFalse(isPasswordValid);
    }

    @Test
    void testLongPasswordWithSpacesShouldBeInvalid() {
        String password = "very long login that contains spaces";
        boolean isPasswordValid = userValidator.isPasswordValid(password.getBytes());
        assertFalse(isPasswordValid);
    }

    @Test
    void testCorrectNameShouldBeValid() {
        String name = "Valid Name";
        boolean isNameValid = userValidator.isNameValid(name);
        assertTrue(isNameValid);
    }

    @Test
    void testNullNameShouldBeInvalid() {
        boolean isNameValid = userValidator.isNameValid(null);
        assertFalse(isNameValid);
    }

    @Test
    void testEmptyNameShouldBeInvalid() {
        String name = "";
        boolean isNameValid = userValidator.isNameValid(name);
        assertFalse(isNameValid);
    }

    @Test
    void testNameWithNumbersAndSpecialSymbolsShouldBeInvalid() {
        String name = "2%fjw48wf@#3hj8";
        boolean isNameValid = userValidator.isNameValid(name);
        assertFalse(isNameValid);
    }

    @Test
    void testCorrectEmailShouldBeValid() {
        String email = "test.valid@email.com";
        boolean isEmailValid = userValidator.isEmailValid(email);
        assertTrue(isEmailValid);
    }

    @Test
    void testNullEmailShouldBeInvalid() {
        boolean isEmailValid = userValidator.isEmailValid(null);
        assertFalse(isEmailValid);
    }

    @Test
    void testEmptyEmailShouldBeInvalid() {
        String email = "";
        boolean isEmailValid = userValidator.isEmailValid(email);
        assertFalse(isEmailValid);
    }

    @Test
    void testEmailWithSpacesShouldBeInvalid() {
        String email = "not valid email";
        boolean isEmailValid = userValidator.isEmailValid(email);
        assertFalse(isEmailValid);
    }

    @Test
    void testEmailWithoutAtSymbolShouldBeInvalid() {
        String email = "not_valid.email.com";
        boolean isEmailValid = userValidator.isEmailValid(email);
        assertFalse(isEmailValid);
    }

    @Test
    void testCorrectPhoneShouldBeValid() {
        String phone = "+123456789";
        boolean isPhoneValid = userValidator.isPhoneValid(phone);
        assertTrue(isPhoneValid);
    }

    @Test
    void testNullPhoneShouldBeInvalid() {
        boolean isPhoneValid = userValidator.isPhoneValid(null);
        assertFalse(isPhoneValid);
    }

    @Test
    void testEmptyPhoneShouldBeInvalid() {
        String phone = "";
        boolean isPhoneValid = userValidator.isPhoneValid(phone);
        assertFalse(isPhoneValid);
    }

    @Test
    void testPhoneWithLettersAndWithoutNumbersShouldBeInvalid() {
        String phone = "not valid phone";
        boolean isPhoneValid = userValidator.isPhoneValid(phone);
        assertFalse(isPhoneValid);
    }

    @Test
    void testPhoneWithSpecialSymbolsShouldBeInvalid() {
        String phone = "2-54tj2#wq*32wh0@5";
        boolean isPhoneValid = userValidator.isPhoneValid(phone);
        assertFalse(isPhoneValid);
    }

    @Test
    void testCorrectUserEntryShouldBeValid() {
        User user = new User();
        user.setLogin("TestLogin");
        user.setPassword("Password1234".getBytes());
        user.setName("Name Surname");
        user.setEmail("test.example@mail.com");
        user.setPhone("+123456789");

        boolean isUserValid = userValidator.isUserValid(user);
        assertTrue(isUserValid);
    }

    @Test
    void testNullUserEntryShouldBeInvalid() {
        boolean isUserValid = userValidator.isUserValid(null);
        assertFalse(isUserValid);
    }

    @Test
    void testUserEntryWithCorrectNotNullFieldShouldBeValid() {
        User user = new User();
        user.setName("Name Surname");

        boolean isUserValid = userValidator.isUserValid(user);
        assertTrue(isUserValid);
    }

    @Test
    void testUserEntryWithAnyIncorrectFieldShouldBeInvalid() {
        User user = new User();
        user.setLogin("TestLogin");
        user.setPassword("invalid password".getBytes());
        user.setName("Name Surname");

        boolean isUserValid = userValidator.isUserValid(user);
        assertFalse(isUserValid);
    }
}
