package by.epamtc.lyskovkirill.restaurant.controller.filter.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.regex.Pattern;

/**
 * The wrapper overrides the getParameterValues(), getParameter()
 * and getHeader() methods to execute the filtering before returning
 * the desired field to the caller. The actual XSS checking and
 * striping is performed in the stripXSS() private method.
 *
 * @author k1ly
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private static final Pattern[] patterns = new Pattern[]{
            // Script fragments
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // src='...'
            Pattern.compile("src[\r\n]*=[\r\n]*'(.*?)'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // lonely script tags
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // eval(...)
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // expression(...)
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // javascript:...
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // vbscript:...
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            // onload(...)=...
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        String[] encodedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        return stripXSS(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return stripXSS(super.getHeader(name));
    }

    private String stripXSS(String value) {
        if (value != null) {
            for (Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }
}
