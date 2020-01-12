package pl.connectis.electronicswebshop.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductQuantityKey implements Serializable {

    @Column(name = "order_id")
    private Long orderID;
    @Column(name = "product_id")
    private Long productID;

    private ProductQuantityKey() {
    }

    public ProductQuantityKey(Long orderID, Long productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityKey that = (ProductQuantityKey) o;
        return orderID.equals(that.orderID) &&
                productID.equals(that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}
