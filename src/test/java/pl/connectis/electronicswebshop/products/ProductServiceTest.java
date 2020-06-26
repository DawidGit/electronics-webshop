package pl.connectis.electronicswebshop.products;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
public class ProductServiceTest {


    private static Product sampleProduct;
    @MockBean
    private ProductsRepository productsRepository;
    @Autowired
    private ProductService productService;

    @BeforeAll
    public static void setUp() {
        //given
        sampleProduct = new Product("sampleProduct", "Admin");
        sampleProduct.setStock(10);
        sampleProduct.setId(1L);
    }

    @Test
    public void whenAddedProduct_thenRepositorySaveCalled() {
        //when
        productService.addProduct(sampleProduct);
        //then
        verify(productsRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void whenAddedProductDto_thenRepositorySaveCalled() {
        //given
        ProductDto sampleProductDto = new ProductDto();
        String addedBy = "Employee";
        String productName = "sampleProduct2";
        int stock = 10;
        sampleProductDto.setAddedBy(addedBy);
        sampleProductDto.setProductName(productName);
        sampleProductDto.setStock(stock);
        Product sampleProductFromDto = new Product(sampleProductDto.getProductName(), sampleProductDto.getAddedBy());
        sampleProductFromDto.setStock(sampleProductDto.getStock());
        Mockito.when(productsRepository.save(any(Product.class))).thenReturn(sampleProductFromDto);
        //when
        Product found = productService.addProduct(sampleProductDto);
        //then
        verify(productsRepository, times(1)).save(any(Product.class));
        assertThat(found.getStock()).isEqualTo(sampleProductDto.getStock());
        assertThat(found.getAddedBy()).isEqualTo(sampleProductDto.getAddedBy());
        assertThat(found.getProductName()).isEqualTo(sampleProductDto.getProductName());
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        //given
        Mockito.when(productsRepository.findById(sampleProduct.getId())).thenReturn(java.util.Optional.of(sampleProduct));
        Long id = 1L;
        //when
        Product found = productService.getProductByID(id);
        //then
        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getProductName()).isEqualTo("sampleProduct");
        assertThat(found.getAddedBy()).isEqualTo("Admin");
        assertThat(found.getStock()).isEqualTo(10);
    }


}
