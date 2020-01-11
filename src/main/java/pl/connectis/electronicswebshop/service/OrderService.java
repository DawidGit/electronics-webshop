package pl.connectis.electronicswebshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.products.ProductDto;
import pl.connectis.electronicswebshop.order.OrderRepository;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder() {
        Order order = new Order();
        orderRepository.save(order);
        return order;


    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
