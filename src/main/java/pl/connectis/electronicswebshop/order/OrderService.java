package pl.connectis.electronicswebshop.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.service.OrderServiceInterface;

import java.security.Principal;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

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
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAllByAddedBy(String addedBy) {
        return orderRepository.findAllByAddedBy(addedBy);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Iterable<OrderLine> findAllProductsByOrder(Order lastOpenOrder) {

        return orderRepository.findById(lastOpenOrder.getId()).get().getProducts();
    }

    public void deleteOrder(Principal principal) {
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        Order order = orderRepository.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        orderRepository.delete(order);
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public boolean addProductToOrder(Product product, int quantity, String username) {
        OrderLine orderLine = null;
        Order lastOpenOrder = orderRepository.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        if (lastOpenOrder == null) {
            lastOpenOrder = addOrder(username);
        }
        for (OrderLine products : findAllProductsByOrder(lastOpenOrder)) {
            if (products.getProduct().equals(product)) {
                orderLine = products;
                orderLine.setQuantity(orderLine.getQuantity() + quantity);
                product.setStock(product.getStock() - quantity);
            }
        }
        if (orderLine == null) {
            orderLine = new OrderLine(lastOpenOrder, product, quantity);
            product.setStock(product.getStock() - quantity);
            lastOpenOrder.getProducts().add(orderLine);
        }
        saveOrder(lastOpenOrder);
        return true;
    }

    public Order removeLine(long orderId, long productId, int quantity) {
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        if (foundOrder.isPresent()) {
            for (Iterator<OrderLine> iterator = foundOrder.get().getProducts().iterator(); iterator.hasNext(); ) {

                OrderLine orderLine = iterator.next();
                Product product = orderLine.getProduct();
                if (orderLine.getProduct().getId().equals(productId)) {
                    if (orderLine.getQuantity() - quantity <= 0) {
                        product.setStock(orderLine.getQuantity() + quantity);
                        iterator.remove();
                        orderLineRepository.delete(orderLine);
                    } else {
                        product.setStock(orderLine.getQuantity() + quantity);
                        orderLine.setQuantity(orderLine.getQuantity() - quantity);
                    }
                }
            }
            return foundOrder.get();
        }
        return null;
    }

    public Order findByAddedByAndOrderStatus(String username, OrderStatus orderStatus) {
        return orderRepository.findByAddedByAndOrderStatus(username, orderStatus);
    }
}
