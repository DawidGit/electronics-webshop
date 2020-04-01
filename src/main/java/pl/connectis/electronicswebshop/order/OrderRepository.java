package pl.connectis.electronicswebshop.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findById(Long id);

    Iterable<Order> findAllByAddedBy(String addedBy);

    Order findByAddedByAndOrderStatus(String addedBy, OrderStatus orderStatus);

    Order findByAddedBy(String username);

    Order findByOrderStatus(OrderStatus orderStatus);

    Order deleteProductById(OrderLine orderLine);


}
