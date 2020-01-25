package pl.connectis.electronicswebshop.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    // Order findById(int id);

    Order findById(int id);

    Iterable<Order> findAllByAddedBy(String addedBy);


//    @Query("Select c from orders where addedBY = :addedBy and orderStatus = :orderStatus")
//    Order findActiveOrder (@Param("addedBy") String addedBy, @Param("orderStatus") String orderStatus);

    Order findByAddedByAndOrderStatus(String addedBy, OrderStatus orderStatus);

    Order findByAddedBy(String username);

    Order findByOrderStatus(OrderStatus orderStatus);

    Order deleteProductById(OrderLine orderLine);


//Order removeOrder(int orderID);


}
