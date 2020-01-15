package pl.connectis.electronicswebshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductsRepository;
import pl.connectis.electronicswebshop.service.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
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

    public Iterable<ProductQuantity> findAllProductsByOrder(Order lastOpenOrder) {
        return orderRepository.findById(lastOpenOrder.getId()).get().getProducts();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public boolean addProductToOrder(Product product, int quantity, String username) {
        ProductQuantity productQuantity = null;
        Order lastOpenOrder = orderRepository.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        if (lastOpenOrder == null) {
            lastOpenOrder = addOrder(username);
        }
        for (ProductQuantity products : findAllProductsByOrder(lastOpenOrder)) {
            if (products.getProduct().equals(product)) {
                productQuantity = products;
                productQuantity.setQuantity(productQuantity.getQuantity() + quantity);
            }
        }
        if (productQuantity == null) {
            productQuantity = new ProductQuantity(lastOpenOrder, product, quantity);
            lastOpenOrder.getProducts().add(productQuantity);
        }
        saveOrder(lastOpenOrder);
        return true;
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
