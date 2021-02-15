package egen.orderprocessing.OrderProcessing.controller;

import egen.orderprocessing.OrderProcessing.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class BatchOrderController {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    private String topic = "batchOrder";

    @PostMapping("/createBatchOrder")
    public String create_batch_order(@RequestBody Order order){
        log.info("Running create_batch_order from BatchOrderController");
        kafkaTemplate.send(topic, order);
        return "Order Placed.";
    }

    @PostMapping("/updateBatchOrder")
    public String update_order(@RequestBody Order order){
        log.info("Running update_order for order Id - " + order.getOrder_id() + " from BatchOrderController");
        Order temp = order;
        kafkaTemplate.send(topic, temp);
        return "Order has been updated.";
    }
}
