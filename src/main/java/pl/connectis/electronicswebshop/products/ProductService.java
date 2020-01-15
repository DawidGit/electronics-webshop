package pl.connectis.electronicswebshop.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.service.IProductService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void addProduct(Product product) {
        productsRepository.save(product);
    }

    @Override
    public void addProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.productName);
        product.setStock(productDto.getStock());

        productsRepository.save(product);
    }

    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Product> getAllProductsByOrderId(Order order) {
        List<Product> selectedList = new ArrayList<>();
        for (Product product : productsRepository.findAll()) {
            if (product.getOrders().equals(order.getId()))
                selectedList.add(product);
        }
        return selectedList;

    }
}
