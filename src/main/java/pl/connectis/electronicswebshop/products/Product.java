package pl.connectis.electronicswebshop.products;

import lombok.*;
import pl.connectis.electronicswebshop.order.OrderLine;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String productName;

    @NonNull
    private String addedBy;

    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private final Collection<OrderLine> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
