package by.epamtc.lyskovkirill.restaurant.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * Class that is used to be the result of
 * {@link Command#execute(HttpServletRequest, HttpServletResponse)} execution.
 * <br>
 * Can contain either page {@link CommandResult#path} to redirect or
 * {@link CommandResult#responseData} to print it.
 *
 * @author k1ly
 */
public class CommandResult {
    private static final String DEFAULT = "/";
    private String path;
    private Map<String, Object> responseData;

    public CommandResult() {
        path = DEFAULT;
    }

    public CommandResult(String path) {
        this.path = path;
    }

    public CommandResult(Map<String, Object> responseData) {
        this.responseData = responseData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String,Object> responseData) {
        this.responseData = responseData;
    }
}