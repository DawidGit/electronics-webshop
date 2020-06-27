package pl.connectis.electronicswebshop.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderLine;
import pl.connectis.electronicswebshop.service.ProductServiceInterface;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductService implements ProductServiceInterface {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productsRepository.save(product);
    }

    @Override
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
