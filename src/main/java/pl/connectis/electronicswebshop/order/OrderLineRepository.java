package pl.connectis.electronicswebshop.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLineKey> {
}
