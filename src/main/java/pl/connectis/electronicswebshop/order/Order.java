package pl.connectis.electronicswebshop.order;

import org.springframework.format.annotation.DateTimeFormat;
import pl.connectis.electronicswebshop.products.Product;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

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
    public final Collection<ProductQuantity> products = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String addedBy;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Collection<ProductQuantity> getProducts() {
        return products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public void removeProduct(Product product, int quantity) {
        for (Iterator<ProductQuantity> iterator = products.iterator();
             iterator.hasNext(); ) {
            ProductQuantity productQuantity = iterator.next();

            if (productQuantity.getOrder().equals(this) &&
                    productQuantity.getProduct().equals(product)) {
                iterator.remove();
                productQuantity.getProduct().getOrders().remove(productQuantity);
                productQuantity.setProduct(null);
                productQuantity.setOrder(null);
                productQuantity.setQuantity(productQuantity.getQuantity() - quantity);
            }
        }
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

    @Override
    public int hashCode() {
        return Objects.hash(products, orderStatus, addedBy);
    }

    @Override
    public String toString() {
        return this.orderStatus.toString();
    }
}
