package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductDto;

public interface ProductService {

    void addProduct(Product product);

    void addProduct(ProductDto productDto);
}
