package egen.orderprocessing.OrderProcessing.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer", schema = "public")

public class Customer {
    @Id
    private String customer_id;
    private String customer_name;
    private String email_id;

    public Customer() {
    }

    public Customer(String customer_id, String customer_name, String email_id) {
        this.customer_id = customer_id;
        this.email_id = email_id;
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}