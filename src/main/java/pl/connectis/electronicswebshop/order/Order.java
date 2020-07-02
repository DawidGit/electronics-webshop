package pl.connectis.electronicswebshop.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "orderDate")
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private final LocalDate orderDate = LocalDate.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final Collection<OrderLine> products = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String addedBy;

}
