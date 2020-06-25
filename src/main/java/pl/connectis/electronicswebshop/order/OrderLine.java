package pl.connectis.electronicswebshop.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.connectis.electronicswebshop.products.Product;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class OrderLine {

    @EmbeddedId
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine that = (OrderLine) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product) &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
