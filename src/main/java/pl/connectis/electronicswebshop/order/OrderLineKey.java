package pl.connectis.electronicswebshop.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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
}
