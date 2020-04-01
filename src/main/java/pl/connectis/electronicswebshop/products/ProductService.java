package pl.connectis.electronicswebshop.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderLine;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductService implements pl.connectis.electronicswebshop.service.ProductService {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

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

    public Product getProductByID(Long productID) {
        if (productsRepository.findById(productID).isPresent())
            return productsRepository.findById(productID).get();
        else return null;
    }

    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Product> getAllProductsByOrderId(Order order) {
        List<Product> selectedList = new ArrayList<>();
        for (Product product : productsRepository.findAll()) {
            for (OrderLine orderLine : product.getOrders()
            ) {
                if (orderLine.getOrder().getId().equals(order.getId()))
                    selectedList.add(product);
            }
        }
        return selectedList;

    }


}
