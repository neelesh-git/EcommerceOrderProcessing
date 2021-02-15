package egen.orderprocessing.OrderProcessing.entity;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    private String address_id;
    private String address_line1;
    private String address_line2;
    private String city;
    private String state;
    private int zip;

    public Address() {
    }

    public Address(String address_id, String address_line1, String address_line2,
                   String city, String state, int zip) {
        this.address_id = address_id;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String getAddress_line2) {
        this.address_line2 = getAddress_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}