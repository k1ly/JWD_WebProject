package by.epamtc.lyskovkirill.restaurant.bean;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Address;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * Java bean class which represents the order.
 *
 * @author k1ly
 */
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private Timestamp orderDate;

    private Timestamp requiredDate;

    private Timestamp deliveryDate;

    private User customer;

    private User manager;

    private Address address;

    private OrderStatus status;

    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(orderDate, order.orderDate) && Objects.equals(requiredDate, order.requiredDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(customer, order.customer) && Objects.equals(manager, order.manager) && Objects.equals(address, order.address) && Objects.equals(status, order.status) && Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, requiredDate, deliveryDate, customer, manager, address, status, orderItems);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", deliveryDate=" + deliveryDate +
                ", customer=" + customer +
                ", manager=" + manager +
                ", address=" + address +
                ", status=" + status +
                ", orderItems=" + orderItems +
                '}';
    }
}
