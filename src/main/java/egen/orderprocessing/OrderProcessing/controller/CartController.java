package egen.orderprocessing.OrderProcessing.controller;

import egen.orderprocessing.OrderProcessing.entity.Cart;
import egen.orderprocessing.OrderProcessing.entity.Items;
import egen.orderprocessing.OrderProcessing.repositories.CartRepository;
import egen.orderprocessing.OrderProcessing.repositories.ItemsRepository;
import egen.orderprocessing.OrderProcessing.services.CartServicesTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartController")
@Slf4j
public class CartController {

    @Autowired
    private CartServicesTemplate cartServicesTemplate;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemsRepository itemsRepository;

    @PostMapping("/addItem/{cart_id}")
    public Items add_items_to_cart(@PathVariable("cart_id") String cart_id, @RequestBody Items items){
        log.info("Running add_items_to_cart for cart Id - " + cart_id + " from CartController");
        Optional<Cart> cart = cartRepository.findById(cart_id);
        items.setCart(cart.get());
        return cartServicesTemplate.addItem(items);
    }

    @GetMapping("getItem/cart/{cart_id}")
    public List<Items> get_items_in_cart(@PathVariable("cart_id") String cart_id){
        log.info("Running get_items_in_cart for cart Id - " + cart_id + " from CartController");
        return cartServicesTemplate.getCartItems(cart_id);
    }

    @GetMapping("/getItems/all")
    public List<Items> get_all_items(){
        log.info("Running get_all_items from CartController");
        return cartServicesTemplate.getAll();
    }

    @GetMapping("/getItemById/{item_id}")
    public Optional<Items> get_item_by_id(@PathVariable("item_id") String item_id){
        log.info("Running get_item_by_id for item Id - " + item_id + " from CartController");
        return cartServicesTemplate.getItem(item_id);
    }

    @GetMapping("/deleteItemById/{item_id}")
    public String delete_item_by_id(@PathVariable ("item_id") String item_id){
        log.info("Running delete_item_by_id for item Id - " + item_id + " from CartController");
        Optional<Items> item = cartServicesTemplate.getItem(item_id);
        itemsRepository.delete(item.get());
        return "Item Removed";
    }

}
