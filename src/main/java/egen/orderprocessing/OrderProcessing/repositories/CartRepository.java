package egen.orderprocessing.OrderProcessing.repositories;

import egen.orderprocessing.OrderProcessing.entity.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
