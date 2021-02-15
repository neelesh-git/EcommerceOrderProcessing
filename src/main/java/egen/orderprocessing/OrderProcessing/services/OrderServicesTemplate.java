package egen.orderprocessing.OrderProcessing.services;

import egen.orderprocessing.OrderProcessing.entity.*;
import java.util.List;

public interface OrderServicesTemplate {
    Order getOrderDetails(String order_id);
    Order createOrder(String cart_id, Order order);
    void createBatchOrder(Order order);
    Order cancelOrder(Order order);
    Cart createCustomer(Customer customer);
    Payments addPayment(String order_id, Payments payments);
    Address addBillingAddress(String order_id, Address address);
    Address addShippingAddress(String order_id, Address address);
    List<Order> getAllOrders();
    void updateBatchOrder(Order order);
}
