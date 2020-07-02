package pl.connectis.electronicswebshop.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.connectis.electronicswebshop.products.Product;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrderLine {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderLineKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderid")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productid")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderLine(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.id = new OrderLineKey(order.getId(), product.getId());
    }
}
