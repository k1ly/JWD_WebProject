package by.epamtc.lyskovkirill.restaurant.controller.constant;

import java.util.Locale;

/**
 * Enum that is required to store acceptable locales of {@link Locale} class.
 *
 * @author k1ly
 */
public enum LocaleEnum {
    ENG(Locale.ENGLISH),
    RUS(new Locale("ru", "RU"));

    final Locale locale;

    LocaleEnum(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
