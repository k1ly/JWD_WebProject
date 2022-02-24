package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents status of the {@link User}.
 *
 * @author k1ly
 */
public class UserStatus implements Serializable {
    public static final String NOT_ACTIVATED = "USER_STATUS_NOT_ACTIVATED";
    public static final String ACTIVE = "USER_STATUS_ACTIVE";
    public static final String SUBSCRIBER = "USER_STATUS_SUBSCRIBER";
    public static final String VIOLATED = "USER_STATUS_VIOLATED";
    public static final String PENALIZED = "USER_STATUS_PENALIZED";

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public UserStatus() {
    }

    public UserStatus(int id) {
        this.id = id;
    }

    public UserStatus(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatus that = (UserStatus) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}