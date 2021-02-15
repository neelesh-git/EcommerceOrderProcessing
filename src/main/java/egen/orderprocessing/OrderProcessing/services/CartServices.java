package egen.orderprocessing.OrderProcessing.services;

import egen.orderprocessing.OrderProcessing.entity.Cart;
import egen.orderprocessing.OrderProcessing.entity.Items;
import egen.orderprocessing.OrderProcessing.repositories.CartRepository;
import egen.orderprocessing.OrderProcessing.repositories.ItemsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CartServices implements CartServicesTemplate {

    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Items addItem(Items item) {
        log.info("Running addItem method in CartServices");
        item.setItem_id(UUID.randomUUID().toString());
        itemsRepository.save(item);
        return item;
    }

    @Override
    public List<Items> getCartItems(String cart_id){
        log.info("Running getCartItems method  for cart id - " + cart_id + " in CartServices");
        Optional<Cart> optionalCart = cartRepository.findById(cart_id);
        Cart cart = optionalCart.get();
        return cart.getCart_items();
    }

    @Override
    public List<Items> getAll(){
        log.info("Running getAll method in CartServices");
        return (List<Items>) itemsRepository.findAll();
    }

    @Override
    public Optional<Items> getItem(String item_id) {
        log.info("Running getItem method for item id - " + item_id + " in CartServices");
        return itemsRepository.findById(item_id);
    }
}
