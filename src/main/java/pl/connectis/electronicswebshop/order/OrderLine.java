package pl.connectis.electronicswebshop.order;

import pl.connectis.electronicswebshop.products.Product;

import javax.persistence.*;
import java.util.Objects;

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

    OrderLine() {
    }

    public OrderLine(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.id = new OrderLineKey(order.getId(), product.getId());
    }

    public OrderLineKey getId() {
        return id;
    }

    public void setId(OrderLineKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
