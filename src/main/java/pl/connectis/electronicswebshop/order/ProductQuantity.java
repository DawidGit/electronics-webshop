package pl.connectis.electronicswebshop.order;

import pl.connectis.electronicswebshop.products.Product;

import javax.persistence.*;

@Entity
public class ProductQuantity {

    @EmbeddedId
    ProductQuantityKey id;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "orderID")
    Order order;

    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "productID")
    Product product;

    int quantity;

    public ProductQuantity() {
    }

    public ProductQuantityKey getId() {
        return id;
    }

    public void setId(ProductQuantityKey id) {
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
}
