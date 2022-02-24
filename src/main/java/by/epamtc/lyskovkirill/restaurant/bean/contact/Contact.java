package by.epamtc.lyskovkirill.restaurant.bean.contact;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * Java bean class which represents the contact.
 *
 * @author k1ly
 */
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Address> addressList;

    private List<String> emailList;

    private List<String> phoneList;

    private List<WorkTime> workTimeList;

    public Contact() {
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public List<WorkTime> getWorkTimeList() {
        return workTimeList;
    }

    public void setWorkTimeList(List<WorkTime> workTimeList) {
        this.workTimeList = workTimeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(addressList, contact.addressList) && Objects.equals(emailList, contact.emailList) && Objects.equals(phoneList, contact.phoneList) && Objects.equals(workTimeList, contact.workTimeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressList, emailList, phoneList, workTimeList);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "addressList=" + addressList +
                ", emailList=" + emailList +
                ", phoneList=" + phoneList +
                ", workTimeList=" + workTimeList +
                '}';
    }
}
