package pl.connectis.electronicswebshop.order;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderID")
    public int orderID;
    @Column(name = "orderDate")
    @DateTimeFormat(pattern="dd-MM-YYYY")
    public final LocalDate orderDate = LocalDate.now();
    @Column(name = "product")
    public String product;
    @Column (name = "quantity")
    public int quantity;

    public Order (){}

    public int getOrderID() {
        return orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
