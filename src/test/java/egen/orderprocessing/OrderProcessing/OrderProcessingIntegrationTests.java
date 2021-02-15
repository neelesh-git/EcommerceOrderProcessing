package egen.orderprocessing.OrderProcessing;

import egen.orderprocessing.OrderProcessing.entity.Order;
import egen.orderprocessing.OrderProcessing.repositories.OrderRepository;
import egen.orderprocessing.OrderProcessing.services.OrderServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class OrderProcessingIntegrationTests {
    private static final String order_id = "e908f72f-e12d-46c9-b3b3-eb84f32cbbce";

    @TestConfiguration
    static class OrderProcessingIntegrationTestsContextConfiguration{
        @Bean
        public OrderServices orderServices(){
            return new OrderServices();
        }
    }
    @Autowired
    private OrderServices orderServices;

    @MockBean
    private OrderRepository orderRepository;

    @Before
    public void setUp(){
        Order order = new Order(order_id, "Placed", null, null, 70, 44, 5.99, 50, 0.00, null, null, null, null);
        Mockito.when(orderRepository.findById(order.getOrder_id()))
                .thenReturn(java.util.Optional.of(order));
    }
    @Test
    public void check_valid_order(){
        Order found = orderServices.getOrderDetails(order_id);

        assertThat(found.getOrder_id())
                .isEqualTo(order_id);
    }
}
