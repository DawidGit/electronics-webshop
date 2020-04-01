package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.order.Order;

import java.util.Optional;

public interface OrderServiceInterface {
    Order addOrder();

    Optional<Order> findById(Long id);

    Order saveOrder(Order order);
}
