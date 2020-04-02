package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductDto;

public interface ProductServiceInterface {

    void addProduct(Product product);

    void addProduct(ProductDto productDto);
}
