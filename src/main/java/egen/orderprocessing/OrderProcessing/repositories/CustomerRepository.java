package egen.orderprocessing.OrderProcessing.repositories;

import egen.orderprocessing.OrderProcessing.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
