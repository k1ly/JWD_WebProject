package by.epamtc.lyskovkirill.restaurant.bean.contact;

import by.epamtc.lyskovkirill.restaurant.bean.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Java bean class which represents the address for {@link Contact}.
 *
 * @author k1ly
 */
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String country;

    private String locality;

    private String street;

    private String house;

    private String apartment;

    private User user;

    public Address() {
    }

    public Address(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(country, address.country) && Objects.equals(locality, address.locality) && Objects.equals(street, address.street) && Objects.equals(house, address.house) && Objects.equals(apartment, address.apartment) && Objects.equals(user, address.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, locality, street, house, apartment, user);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apartment='" + apartment + '\'' +
                ", user=" + user +
                '}';
    }
}
