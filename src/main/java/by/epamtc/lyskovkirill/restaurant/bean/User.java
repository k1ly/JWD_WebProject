package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * Java bean class which represents the user.
 *
 * @author k1ly
 */
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String login;

    private byte[] password;

    private String name;

    private String email;

    private String phone;

    private Order cart;

    private UserRole role;

    private UserStatus status;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Order getCart() {
        return cart;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login)
                && Arrays.equals(password, user.password) && Objects.equals(name, user.name)
                && Objects.equals(email, user.email) && Objects.equals(phone, user.phone)
                && Objects.equals(cart, user.cart) && Objects.equals(role, user.role)
                && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, login, name, email, phone, cart, role, status);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cart=" + cart +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
