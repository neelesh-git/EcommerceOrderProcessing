package egen.orderprocessing.OrderProcessing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @Column(name = "order_id")
    private String order_id;
    private String order_status;
    private Date create_date;
    private Date modified_date;
    private double order_total;
    private double tax;
    private double total;
    private double shipping_charges;
    private double subtotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> payments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id", referencedColumnName = "address_id")
    private Address billing_address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "address_id")
    private Address shipping_address;

    public Order() {
    }

    public Order(String order_id){
        this.order_id = order_id;
    }

    public Order(double order_total, double tax, double shipping_charges, double subtotal) {
        this.order_total = order_total;
        this.tax = tax;
        this.total = total;
        this.shipping_charges = shipping_charges;
        this.subtotal = subtotal;

    }

    public Order(String order_id, String order_status, Date create_date, Date modified_date,
                 double order_total, double tax, double total, double shipping_charges,
                 double subtotal, Cart cart, List<Payments> payments,
                 Address billing_address, Address shipping_address)  {
        this.order_id = order_id;
        this.order_status = order_status;
        this.create_date = create_date;
        this.modified_date = modified_date;
        this.order_total = order_total;
        this.tax = tax;
        this.total = total;
        this.shipping_charges = shipping_charges;
        this.subtotal = subtotal;
        this.cart = cart;
        this.payments = payments;
        this.billing_address = billing_address;
        this.shipping_address = shipping_address;
    }

    public Order(double order_total, double tax, double total, double shipping_charges,
                 double subtotal){
        this.order_total = order_total;
        this.tax = tax;
        this.total = total;
        this.shipping_charges = shipping_charges;
        this.subtotal = subtotal;
    }

    public Address getShipping_address() { return shipping_address; }

    public void setShipping_address(Address shipping_address) { this.shipping_address = shipping_address; }

    public Address getBilling_address() { return billing_address; }

    public void setBilling_address(Address billing_address) { this.billing_address = billing_address; }

    public List<Payments> getPayments() { return payments; }

    public void setPayments(List<Payments> payments) { this.payments = payments; }

    public String getOrder_id() { return order_id; }

    public void setOrder_id(String order_id) { this.order_id = order_id; }

    public String getOrder_status() { return order_status; }

    public void setOrder_status(String order_status) { this.order_status = order_status; }

    public Date getCreate_date() { return create_date; }

    public void setCreate_date(Date createDate) { this.create_date = createDate; }

    public Date getModified_date() { return modified_date; }

    public void setModified_date(Date modifiedDate) { this.modified_date = modifiedDate; }

    public double getOrder_total() { return order_total; }

    public void setOrder_total(double orderTotal) { this.order_total = orderTotal; }

    public double getTax() { return tax; }

    public void setTax(double tax) { this.tax = tax; }

    public double getTotal() { return total; }

    public void setTotal(double total) { this.total = total; }

    public double getShipping_charges() { return shipping_charges; }

    public void setShipping_charges(double shipping_charges) { this.shipping_charges = shipping_charges; }

    public double getSubtotal() { return subtotal; }

    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
