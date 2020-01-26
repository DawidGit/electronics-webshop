package pl.connectis.electronicswebshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductsRepository;
import pl.connectis.electronicswebshop.service.IOrderService;

import java.security.Principal;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductsRepository productsRepository;

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

    public Iterable<OrderLine> findAllProductsByOrder(Order lastOpenOrder) {

        return orderRepository.findById(lastOpenOrder.getId()).get().getProducts();
    }

    public void deleteOrder(Principal principal, OrderStatus orderStatus) {
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
            }
        }
        if (orderLine == null) {
            orderLine = new OrderLine(lastOpenOrder, product, quantity);
            lastOpenOrder.getProducts().add(orderLine);
        }
        saveOrder(lastOpenOrder);
        return true;
    }

    public Order removeLine(long orderId, Product product, int quantity) {
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        if (foundOrder.isPresent()) {
            for (Iterator<OrderLine> iterator = foundOrder.get().getProducts().iterator();
                 iterator.hasNext(); ) {

                OrderLine orderLine = iterator.next();
                if (orderLine.getProduct().equals(product)) {
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


    //   public List<Product> getAllPurchases (Order order) {

//        List<Product> selectedList = new ArrayList<>();
//
//        for (Product product : productsRepository.findAll()) {
////            if (purchase.getOrderID().equals(order.getId()))
//            selectedList.add(product); }
//        return selectedList;
//    }


}
