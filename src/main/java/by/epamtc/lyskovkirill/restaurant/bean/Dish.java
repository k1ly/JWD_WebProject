package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents the dish.
 *
 * @author k1ly
 */
public class Dish implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String composition;

    private String image;

    private Integer weight;

    private Double price;

    private Integer discount;

    private DishCategory category;

    public Dish() {}

    public Dish(int id) {
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

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id && Objects.equals(name, dish.name) && Objects.equals(composition, dish.composition) && Objects.equals(image, dish.image) && Objects.equals(weight, dish.weight) && Objects.equals(price, dish.price) && Objects.equals(discount, dish.discount) && Objects.equals(category, dish.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, composition, image, weight, price, discount, category);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", composition='" + composition + '\'' +
                ", image='" + image + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", discount=" + discount +
                ", category=" + category +
                '}';
    }
}
