package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents the order item for an {@link Order}.
 *
 * @author k1ly
 */
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Order order;

    private Dish dish;

    private Integer quantity;

    private Double totalPrice;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem item = (OrderItem) o;
        return Objects.equals(order, item.order) && Objects.equals(dish, item.dish) && Objects.equals(quantity, item.quantity) && Objects.equals(totalPrice, item.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, dish, quantity, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", dish=" + dish +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
