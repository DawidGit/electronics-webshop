package pl.connectis.electronicswebshop.order;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "orderDate")
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private final LocalDate orderDate = LocalDate.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final Collection<OrderLine> products = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String addedBy;

    public Order() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(products, order.products) &&
                orderStatus == order.orderStatus &&
                addedBy.equals(order.addedBy);
    }


//    @Override
//    public String toString() {
//        return this.orderStatus.toString();
//    }
}
