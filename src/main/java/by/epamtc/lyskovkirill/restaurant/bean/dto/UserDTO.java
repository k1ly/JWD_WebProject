package by.epamtc.lyskovkirill.restaurant.bean.dto;

import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.bean.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean DTO class which is used to store {@link User}
 * data on the client side.
 *
 * @author k1ly
 */
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String roleName;

    private String statusName;

    private Integer cartOrderId;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String roleName, String statusName, Integer cartOrderId) {
        this.id = id;
        this.name = name;
        this.roleName = roleName;
        this.statusName = statusName;
        this.cartOrderId = cartOrderId;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getCartOrderId() {
        return cartOrderId;
    }

    public void setCartOrderId(Integer cartOrderId) {
        this.cartOrderId = cartOrderId;
    }

    public static UserDTO convert(User user) {
        return new UserDTO(user.getId(), user.getName(),
                user.getRole() != null ? user.getRole().getName() : null,
                user.getStatus() != null ? user.getStatus().getName() : null,
                user.getCart() != null ? user.getCart().getId() : null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(name, userDTO.name) && Objects.equals(roleName, userDTO.roleName) && Objects.equals(statusName, userDTO.statusName) && Objects.equals(cartOrderId, userDTO.cartOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roleName, statusName, cartOrderId);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", cartOrderId=" + cartOrderId +
                '}';
    }
}
