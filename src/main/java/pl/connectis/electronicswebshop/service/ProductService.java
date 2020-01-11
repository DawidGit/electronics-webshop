package pl.connectis.electronicswebshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductDto;
import pl.connectis.electronicswebshop.products.ProductsRepository;


@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void addProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.productName);
        product.setStock(productDto.getStock());

    }

    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }
}
