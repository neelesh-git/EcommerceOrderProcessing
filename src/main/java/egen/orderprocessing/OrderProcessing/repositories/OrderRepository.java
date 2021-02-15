package egen.orderprocessing.OrderProcessing.repositories;

import egen.orderprocessing.OrderProcessing.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}
