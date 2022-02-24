package by.epamtc.lyskovkirill.restaurant.bean.dto;

import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean DTO class which is used to store {@link OrderItem}
 * data on the client side.
 *
 * @author k1ly
 */
public class OrderItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer dishId;

    private Integer quantity;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer dishId, Integer quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static OrderItemDTO convert(OrderItem orderItem) {
        return new OrderItemDTO(orderItem.getDish().getId(), orderItem.getQuantity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDTO that = (OrderItemDTO) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, quantity);
    }

    @Override
    public String toString() {
        return "CartItemDTO{" +
                "dishId=" + dishId +
                ", quantity=" + quantity +
                '}';
    }
}
