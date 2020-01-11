package pl.connectis.electronicswebshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductDto;

import java.util.Collections;


@Service
@Transactional
public class ProductService implements IProductService {


    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void addProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.productName);
        product.setQuantity(productDto.getQuantity());

    }
}
