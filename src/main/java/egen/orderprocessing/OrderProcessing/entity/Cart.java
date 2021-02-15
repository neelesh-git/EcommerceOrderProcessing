package egen.orderprocessing.OrderProcessing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart", schema = "public")
public class Cart {
    @Id
    @Column(name = "cart_id")
    private String cart_id;
    private String customer_id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Items> cart_items;

    @OneToOne
    @JsonIgnore
    private Customer customer;

    public Cart() {
    }

    public Cart(String customer_id) {
        this.customer_id = customer_id;
    }

    public Cart(String cart_id, String customer_id, List<Items> cart_items) {
        this.cart_id = cart_id;
        this.customer_id = customer_id;
        this.cart_items = cart_items;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public List<Items> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<Items> cart_items) {
        this.cart_items = cart_items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id='" + cart_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", cart_items=" + cart_items +
                ", customer=" + customer +
                '}';
    }
}