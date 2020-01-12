package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.order.Order;

public interface IOrderService {
  Order addOrder();

  Order findById(int id);

  Order saveOrder(Order order);
}
