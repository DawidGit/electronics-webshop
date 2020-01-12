package pl.connectis.electronicswebshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderRepository;
import pl.connectis.electronicswebshop.order.OrderStatus;
import pl.connectis.electronicswebshop.order.ProductQuantity;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder() {
        Order order = new Order();
        order.setAddedBy("Anonymous");
        order.setOrderStatus(OrderStatus.OPEN);
        orderRepository.save(order);
        return order;
    }

    public Order addOrder(String username) {
        Order order = new Order();
        order.setAddedBy(username);
        order.setOrderStatus(OrderStatus.OPEN);
        orderRepository.save(order);
        return order;
    }


    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAllByAddedBy(String addedBy) {
        return orderRepository.findAllByAddedBy(addedBy);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Iterable<ProductQuantity> findAllProductsByOrder(Order lastOpenOrder) {
        return orderRepository.findById(lastOpenOrder.getId()).get().getProducts();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
