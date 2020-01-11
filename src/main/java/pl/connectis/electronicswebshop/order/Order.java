package pl.connectis.electronicswebshop.order;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderID")
    public Long orderID;
    @Column(name = "orderDate")
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    public final LocalDate orderDate = LocalDate.now();

    @OneToMany(mappedBy = "order")
    public Collection<ProductQuantity> quantities;

    public Order() {
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Collection<ProductQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Collection<ProductQuantity> quantities) {
        this.quantities = quantities;
    }
}
