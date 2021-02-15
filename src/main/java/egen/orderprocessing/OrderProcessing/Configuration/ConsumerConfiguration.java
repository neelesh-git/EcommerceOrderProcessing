package egen.orderprocessing.OrderProcessing.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import egen.orderprocessing.OrderProcessing.entity.Cart;
import egen.orderprocessing.OrderProcessing.entity.Order;
import egen.orderprocessing.OrderProcessing.services.OrderServicesTemplate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
public class ConsumerConfiguration {

    @Autowired
    private OrderServicesTemplate orderServicesTemplate;

    @KafkaListener(groupId = "batchOrder-1", topics = "batchOrder", containerFactory = "orderConcurrentKafkaListenerContainerFactory")
    public void getMessage(ConsumerRecords<String, Order> records) throws JsonProcessingException{

        for(ConsumerRecord<String, Order> record: records){
            if(record.value().getPayments() == null){
                try{
                    Order order = record.value();
                    orderServicesTemplate.updateBatchOrder(order);

                }
                catch (Exception e){
                    throw new NotFoundException("Invalid! Order Id - " + record.value().getOrder_id() + " not found.");
                }
            }
            else{
                Order order = record.value();
                orderServicesTemplate.createBatchOrder(order);
            }
        }
    }
}
