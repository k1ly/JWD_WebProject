package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents category of the {@link Dish}.
 *
 * @author k1ly
 */
public class DishCategory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public DishCategory() {
    }

    public DishCategory(int id) {
        this.id = id;
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
        DishCategory that = (DishCategory) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DishCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
