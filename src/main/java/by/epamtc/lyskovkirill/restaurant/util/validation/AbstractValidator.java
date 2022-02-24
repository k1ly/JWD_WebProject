package by.epamtc.lyskovkirill.restaurant.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class that has method {@link AbstractValidator#isFieldValid(String, String)}
 * to check for the field validity using regular expression pattern.
 *
 * @author k1ly
 */
public abstract class AbstractValidator {

    public boolean isFieldValid(String pattern, String field) {
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher matcher = patternCompile.matcher(field);
        return matcher.matches();
    }
}
