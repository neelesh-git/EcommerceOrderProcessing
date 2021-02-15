package egen.orderprocessing.OrderProcessing.services;

import egen.orderprocessing.OrderProcessing.entity.*;
import egen.orderprocessing.OrderProcessing.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderServices implements OrderServicesTemplate {

    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    public PaymentsRepository paymentsRepository;
    @Autowired
    public AddressRepository addressRepository;
    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public Order getOrderDetails(String order_id) {
        log.info("Running getOrderDetails method for order id - " + order_id + " in OrderServices");
        Optional<Order> optionalOrders = orderRepository.findById(order_id);
        Order temp = optionalOrders.get();
        temp.setOrder_status("Placed");
        orderRepository.save(temp);
        return optionalOrders.orElse(null);
    }

    @Override
    public List<Order> getAllOrders(){
        log.info("Running getAllOrders method in OrderServices");
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order createOrder(String cart_id, Order orders) {
        log.info("Running createOrder method for cart id - " + cart_id + " in OrderServices");
        Order temp = orders;
        temp.setOrder_id(UUID.randomUUID().toString());
        temp.setOrder_status("Placed");
        temp.setCreate_date(Date.from(Instant.now()));

        Optional<Cart> cart = cartRepository.findById(cart_id);
        Cart cart1 = cart.get();
        temp.setCart(cart1);
        orderRepository.save(temp);
        return temp;
    }

    @Override
    public void createBatchOrder(Order order) {
        log.info("Running createBatchOrder method in OrderServices");
        Order temp = new Order(UUID.randomUUID().toString());
        temp.setOrder_status("Placed");
        temp.setCreate_date(Date.from(Instant.now()));
        temp.setOrder_total(order.getOrder_total());
        temp.setTax(order.getTax());
        temp.setTotal(order.getTotal());
        temp.setShipping_charges(order.getShipping_charges());
        temp.setSubtotal(order.getSubtotal());

        Customer customer = new Customer();
        customer.setCustomer_id(order.getCart().getCustomer_id());
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer_id(customer.getCustomer_id());
        cart.setCart_id(order.getCart().getCart_id());
        temp.setCart(cart);
        cartRepository.save(cart);

        List<Items> itemsList = order.getCart().getCart_items();
        for(Items items : itemsList){
            items.setCart(cart);
            itemsRepository.save(items);
        }
        orderRepository.save(temp);
    }

    @Override
    public void updateBatchOrder(Order order){
        log.info("Running updateBatchOrder method in OrderServices");
        Order temp = order;
        temp.setOrder_status("Placed");
        temp.setModified_date(Date.from(Instant.now()));
        temp.setOrder_total(order.getOrder_total());
        temp.setTax(order.getTax());
        temp.setTotal(order.getTotal());
        temp.setShipping_charges(order.getShipping_charges());
        temp.setSubtotal(order.getSubtotal());

        Customer customer = new Customer();
        customer.setCustomer_id(order.getCart().getCustomer_id());
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer_id(customer.getCustomer_id());
        cart.setCart_id(order.getCart().getCart_id());
        temp.setCart(cart);
        cartRepository.save(cart);

        List<Items> itemsList = order.getCart().getCart_items();
        itemsRepository.deleteAll();
//        for(Items items : itemsList){
//            items.setCart(cart);
//            if(!itemsRepository.existsById(items.getItem_id()))
//                itemsRepository.save(items);
//        }

        orderRepository.save(temp);
    }

    @Override
    public Address addBillingAddress(String order_id, Address address){
        log.info("Running addBillingAddress method for order id - " + order_id + " in OrderServices");
        Address temp = address;
        temp.setAddress_id(UUID.randomUUID().toString());
        Optional<Order> optionalOrders = orderRepository.findById(order_id);
        Order order = optionalOrders.get();
        order.setBilling_address(temp);
        orderRepository.save(order);
        addressRepository.save(temp);
        return temp;
    }

    @Override
    public Address addShippingAddress(String order_id, Address address){
        log.info("Running addShippingAddress method for order id - " + order_id + " in OrderServices");
        Address temp = address;
        temp.setAddress_id(UUID.randomUUID().toString());
        Optional<Order> optionalOrders = orderRepository.findById(order_id);
        Order order = optionalOrders.get();
        order.setShipping_address(temp);
        orderRepository.save(order);
        addressRepository.save(temp);
        return temp;
    }

    @Override
    public Payments addPayment(String order_id, Payments payments){
        log.info("Running addPayment method for order id - " + order_id + " in OrderServices");
        payments.setPayment_id(UUID.randomUUID().toString());
        paymentsRepository.save(payments);
        return payments;
    }

    @Override
    public Order cancelOrder(Order order){
        log.info("Running cancelOrder method for order id - " + order.getOrder_id() + " in OrderServices");
        Optional<Order> optionalOrders = orderRepository.findById(order.getOrder_id());
            order.setOrder_status("Cancelled");
            order.setModified_date(Date.from(Instant.now()));
            orderRepository.save(order);
            return order;
    }

    @Override
    public Cart createCustomer(Customer customer) {
        log.info("Running createCustomer method in OrderServices");
        Customer customer1 = customer;
        customer1.setCustomer_id(UUID.randomUUID().toString());
        customerRepository.save(customer1);

        Cart cart = new Cart();
        cart.setCustomer_id(customer1.getCustomer_id());
        cart.setCart_id(UUID.randomUUID().toString());
        cartRepository.save(cart);
        return cart;
    }
}

