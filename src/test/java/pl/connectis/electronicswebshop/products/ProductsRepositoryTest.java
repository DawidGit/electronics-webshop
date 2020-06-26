package pl.connectis.electronicswebshop.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void whenFindById_thenReturnProduct() {
        //given
        Product sampleProduct = new Product("sampleProduct", "Admin");
        entityManager.persist(sampleProduct);
        entityManager.flush();

        //when
        Product found = productsRepository.findById(1);

        //then
        assertThat(found.getProductName()).isEqualTo(sampleProduct.getProductName());
        assertThat(found.getAddedBy()).isEqualTo(sampleProduct.getAddedBy());
    }

}
