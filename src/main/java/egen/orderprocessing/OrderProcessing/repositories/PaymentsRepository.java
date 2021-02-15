package egen.orderprocessing.OrderProcessing.repositories;

import egen.orderprocessing.OrderProcessing.entity.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payments, String> {
}
