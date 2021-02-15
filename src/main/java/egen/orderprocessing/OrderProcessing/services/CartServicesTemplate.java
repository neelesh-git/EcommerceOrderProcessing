package egen.orderprocessing.OrderProcessing.services;

import egen.orderprocessing.OrderProcessing.entity.Items;
import java.util.List;
import java.util.Optional;

public interface CartServicesTemplate {
    Items addItem(Items item);
    Optional<Items> getItem(String item_id);
    List<Items> getAll();
    List<Items> getCartItems(String cart_id);
}
