package by.epamtc.lyskovkirill.restaurant.util.validation;

import by.epamtc.lyskovkirill.restaurant.bean.User;

/**
 * Class that is responsible for validating {@link User} entry.
 *
 * @author k1ly
 */
public class UserValidator extends AbstractValidator {
    private final static String LOGIN_PATTERN = "^[A-Za-z]\\w{5,20}$";
    private final static String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private final static String NAME_PATTERN = "^(\\p{L})+([(. )'-](\\p{L})+)*$";
    private final static String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
    private final static String PHONE_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private static final UserValidator instance = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        return instance;
    }

    public boolean isLoginValid(String login) {
        return login != null && isFieldValid(LOGIN_PATTERN, login);
    }

    public boolean isPasswordValid(byte[] password) {
        return password != null && isFieldValid(PASSWORD_PATTERN, new String(password));
    }

    public boolean isNameValid(String name) {
        return name != null && isFieldValid(NAME_PATTERN, name);
    }

    public boolean isEmailValid(String email) {
        return email != null && isFieldValid(EMAIL_PATTERN, email);
    }

    public boolean isPhoneValid(String phone) {
        return phone != null && isFieldValid(PHONE_PATTERN, phone);
    }

    public boolean isUserValid(User user) {
        if (user == null)
            return false;
        boolean isUserValid = true;
        if (user.getLogin() != null)
            isUserValid = isLoginValid(user.getLogin());
        if (user.getPassword() != null)
            isUserValid &= isPasswordValid(user.getPassword());
        if (user.getName() != null)
            isUserValid &= isNameValid(user.getName());
        if (user.getEmail() != null)
            isUserValid &= isEmailValid(user.getEmail());
        if (user.getPhone() != null)
            isUserValid &= isPhoneValid(user.getPhone());
        return isUserValid;
    }
}
