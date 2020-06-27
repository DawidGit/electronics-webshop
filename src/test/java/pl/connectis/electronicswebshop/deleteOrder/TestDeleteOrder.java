package pl.connectis.electronicswebshop.deleteOrder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderRepository;
import pl.connectis.electronicswebshop.order.OrderService;
import pl.connectis.electronicswebshop.order.OrderStatus;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
public class TestDeleteOrder {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Test
    void deleteOrderTest() {

        //given

        String userNameTest = "Anonymous";
        OrderStatus orderStatusTest = OrderStatus.OPEN;
        Order deletedOrderTest;
        Principal principal = httpServletRequest.getUserPrincipal();

        //when

        orderService.deleteOrder(principal);
        deletedOrderTest = orderRepository.findByAddedByAndOrderStatus(userNameTest, orderStatusTest);

        //then

        Assertions.assertNull(deletedOrderTest);

    }
}
