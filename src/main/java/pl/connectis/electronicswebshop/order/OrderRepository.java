package pl.connectis.electronicswebshop.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

Order findByOrderID (int orderID);


//Order removeOrder(int orderID);


}
