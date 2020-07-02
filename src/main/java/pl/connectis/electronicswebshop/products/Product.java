package pl.connectis.electronicswebshop.products;

import lombok.*;
import pl.connectis.electronicswebshop.order.OrderLine;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private String productName;

    @NonNull
    private String addedBy;

    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private final Collection<OrderLine> orders = new HashSet<>();

}
