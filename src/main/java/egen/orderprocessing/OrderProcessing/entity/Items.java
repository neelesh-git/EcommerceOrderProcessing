package egen.orderprocessing.OrderProcessing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "items", schema = "public")
public class Items {
    @Id
    private String item_id;
    private String item_name;
    private int quantity;
    private double price;
    private double shipping_charge;
    private String delivery_mode;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Cart.class)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    public Items() {
    }

    public Items(String delivery_mode, String description, String item_id, String item_name,
                 int quantity, double price, double shipping_charge) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
        this.shipping_charge = shipping_charge;
        this.delivery_mode = delivery_mode;
        this.description = description;
    }

    public String getDelivery_mode() {
        return delivery_mode;
    }

    public void setDelivery_mode(String delivery_mode) {
        this.delivery_mode = delivery_mode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShipping_charge() {
        return shipping_charge;
    }

    public void setShipping_charge(double shipping_charge) {
        this.shipping_charge = shipping_charge;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
