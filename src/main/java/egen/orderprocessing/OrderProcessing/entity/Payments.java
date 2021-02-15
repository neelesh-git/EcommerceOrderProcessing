package egen.orderprocessing.OrderProcessing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
public class Payments {
    @Id
    private String payment_id;
    private String payment_type;
    private String confirmation_number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    public Payments() {
    }

    public Payments(String payment_id, String payment_type, String confirmation_number) {
        this.payment_id = payment_id;
        this.payment_type = payment_type;
        this.confirmation_number = confirmation_number;
    }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }

    public String getPayment_id() { return payment_id; }

    public void setPayment_id(String payment_id) { this.payment_id = payment_id; }

    public String getPayment_type() { return payment_type; }

    public void setPayment_type(String payment_type) { this.payment_type = payment_type; }

    public String getConfirmation_number() { return confirmation_number; }

    public void setConfirmation_number(String confirmation_number) { this.confirmation_number = confirmation_number; }
}