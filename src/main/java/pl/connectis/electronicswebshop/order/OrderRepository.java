package pl.connectis.electronicswebshop.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findAllByAddedBy(String addedBy);

    Order findByAddedByAndOrderStatus(String addedBy, OrderStatus orderStatus);


}
