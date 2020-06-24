package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.order.Order;

public interface OrderServiceInterface {
    Order addOrder();

    Order saveOrder(Order order);
}
