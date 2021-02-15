package egen.orderprocessing.OrderProcessing.repositories;

import egen.orderprocessing.OrderProcessing.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, String> {

    List<Items> findByCart(String cart);
}
