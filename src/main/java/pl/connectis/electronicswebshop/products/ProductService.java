package pl.connectis.electronicswebshop.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductService {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product addProduct(Product product) {
        return productsRepository.save(product);
    }

    public Product addProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setStock(productDto.getStock());
        product.setAddedBy(productDto.getAddedBy());

        return productsRepository.save(product);
    }

    public Product getProductByID(Long productID) {
            return productsRepository.findById(productID).orElse(null);
    }

    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }


}
