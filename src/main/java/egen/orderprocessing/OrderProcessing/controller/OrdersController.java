package egen.orderprocessing.OrderProcessing.controller;

import egen.orderprocessing.OrderProcessing.entity.*;
import egen.orderprocessing.OrderProcessing.repositories.OrderRepository;
import egen.orderprocessing.OrderProcessing.repositories.PaymentsRepository;
import egen.orderprocessing.OrderProcessing.services.OrderServicesTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderController")
@Slf4j
public class OrdersController {

    @Autowired
    private OrderServicesTemplate orderServicesTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @PostMapping("/createCustomer")
    public Cart add_customer(@RequestBody Customer customer){
        log.info("Running add_customer from OrdersController");
        return orderServicesTemplate.createCustomer(customer);
    }

    @GetMapping("/getOrders/all")
    public List<Order> get_all_orders(){
        log.info("Running get_all_orders from OrdersController");
        return orderServicesTemplate.getAllOrders();
    }

    @PostMapping("/addBilling/{order_id}")
    public Address add_billing(@PathVariable("order_id") String order_id, @RequestBody Address address){
        log.info("Running add_billing for order Id - " + order_id + " from OrdersController");
        return orderServicesTemplate.addBillingAddress(order_id, address);
    }

    @PostMapping("/addShipping/{order_id}")
    public Address add_shipping(@PathVariable("order_id") String order_id, @RequestBody Address address){
        log.info("Running add_shipping for order Id - " + order_id + " from OrdersController");
        return orderServicesTemplate.addShippingAddress(order_id, address);
    }

    @GetMapping("/getOrderById/{order_id}")
    public Order get_order_by_id(@PathVariable("order_id") String order_id){
        log.info("Running get_order_by_id for order Id - " + order_id + " from OrdersController");
        return orderServicesTemplate.getOrderDetails(order_id);
    }

    @PostMapping("/createOrder/{cart_id}")
    public Order place_order(@PathVariable("cart_id") String cart_id, @RequestBody Order order){
        log.info("Running place_order for cart Id - " + cart_id + " from OrdersController");
        return orderServicesTemplate.createOrder(cart_id, order);
    }

    @PostMapping("/addPaymentById/{order_id}")
    public Payments add_payment(@PathVariable("order_id") String order_id, @RequestBody Payments payments){
        log.info("Running add_payment for order Id - " + order_id + " from OrdersController");
        Optional<Order> order = orderRepository.findById(order_id);
        payments.setOrder(order.get());
        return orderServicesTemplate.addPayment(order_id, payments);
    }

    @GetMapping("/deletePaymentById/{payment_id}")
    public String delete_payment(@PathVariable("payment_id") String payment_id){
        log.info("Running delete_payment for payment Id - " + payment_id + " from OrdersController");
        Optional<Payments> payments = paymentsRepository.findById(payment_id);
        Payments pay = payments.get();
        paymentsRepository.delete(pay);
        return "Deleted Payment.";
    }

    @GetMapping("/deleteOrderById/{order_id}")
    public Order delete_order(@PathVariable ("order_id") String order_id){
        log.info("Running delete_order for order Id - " + order_id + " from OrdersController");
        Optional<Order> order = orderRepository.findById(order_id);
        return orderServicesTemplate.cancelOrder(order.get());
    }
}
