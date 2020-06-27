package pl.connectis.electronicswebshop.service;

import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductDto;

public interface ProductServiceInterface {

    Product addProduct(Product product);

    Product addProduct(ProductDto productDto);
}
