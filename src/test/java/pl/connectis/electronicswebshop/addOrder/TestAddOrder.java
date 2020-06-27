package pl.connectis.electronicswebshop.addOrder;

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

@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
public class TestAddOrder {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void addOrderTest() {

        //given
        Order addedOrderTest;
        String userName = "Anonymous1";
        OrderStatus orderStatusTest = OrderStatus.OPEN;

        //when

        orderService.addOrder(userName);


        addedOrderTest = orderRepository.findByAddedByAndOrderStatus(userName, orderStatusTest);

        //then

        Assertions.assertNotEquals(null, addedOrderTest.getId());

    }

}
