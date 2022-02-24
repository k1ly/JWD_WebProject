package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents status of the {@link Order}.
 *
 * @author k1ly
 */
public class OrderStatus implements Serializable {
    public static final String CREATED = "ORDER_STATUS_CREATED";
    public static final String AWAITING = "ORDER_STATUS_AWAITING";
    public static final String CANCELED = "ORDER_STATUS_CANCELED";
    public static final String PREPARING = "ORDER_STATUS_PREPARING";
    public static final String READY = "ORDER_STATUS_READY";
    public static final String NOT_PAID = "ORDER_STATUS_NOT_PAID";
    public static final String FINISHED = "ORDER_STATUS_FINISHED";

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public OrderStatus() {
    }

    public OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus(String name) {
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
        OrderStatus that = (OrderStatus) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}