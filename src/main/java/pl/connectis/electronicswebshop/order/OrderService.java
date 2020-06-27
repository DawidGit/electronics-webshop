package pl.connectis.electronicswebshop.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.products.Product;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public Order addOrder() {
        return addOrder("Anonymous");
    }

    public Order addOrder(String username) {
        Order order = new Order();
        order.setAddedBy(username);
        order.setOrderStatus(OrderStatus.OPEN);
        orderRepository.save(order);
        return order;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Iterable<OrderLine> findAllProductsByOrder(Order lastOpenOrder) {

        return orderRepository.findById(lastOpenOrder.getId()).get().getProducts();
    }

    public boolean deleteOrder(Principal principal) {

        String username = ((principal == null) ? "Anonymous" : principal.getName());
        Order order = orderRepository.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        if (order != null) {
            orderRepository.delete(order);
            return true;
        } else {
            return false;
        }
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
            }
        }
        if (orderLine == null) {
            orderLine = new OrderLine(lastOpenOrder, product, quantity);
            lastOpenOrder.getProducts().add(orderLine);
        }
        product.setStock(product.getStock() - quantity);
        saveOrder(lastOpenOrder);
        return true;
    }

    public Order removeProduct(long orderId, long productId, int quantity) {
        Optional<Order> foundOrder = orderRepository.findById(orderId);


        if (foundOrder.isPresent()) {
            Collection<OrderLine> orderLinesCollection = foundOrder.get().getProducts();
            OrderLine orderLine = null;
            for (OrderLine currentOrderLine : orderLinesCollection) {
                if (currentOrderLine.getProduct().getId().equals(productId)) {
                    orderLine = currentOrderLine;
                    break;
                }
            }
            if (orderLine != null) {
                Product product = orderLine.getProduct();
                if (orderLine.getQuantity() - quantity <= 0) {
                    product.setStock(product.getStock() + orderLine.getQuantity());
                    orderLinesCollection.remove(orderLine);
                    orderLineRepository.delete(orderLine);
                } else {
                    product.setStock(product.getStock() + quantity);
                    orderLine.setQuantity(orderLine.getQuantity() - quantity);

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
