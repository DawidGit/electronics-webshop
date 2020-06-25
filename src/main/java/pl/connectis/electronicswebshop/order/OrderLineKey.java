package pl.connectis.electronicswebshop.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class OrderLineKey implements Serializable {

    @NonNull
    @Column(name = "order_id")
    private Long orderID;
    @NonNull
    @Column(name = "product_id")
    private Long productID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineKey that = (OrderLineKey) o;
        return orderID.equals(that.orderID) &&
                productID.equals(that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}
