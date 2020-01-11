package pl.connectis.electronicswebshop.products;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.connectis.electronicswebshop.persistence.model.User;


@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

//@PostMapping("/order/{productName}")
//public Order newOrder (@PathVariable String productName) {
//   return orderRepository.createOrder(new Product(productName));
//}

    //Czynności po stronie Pracownika

    @PostMapping("/addProduct/{productName}")
    public Product addProduct(@PathVariable String productName, User user) {
        Product prod = new Product();
        return productsRepository.save(prod);
    }

}



