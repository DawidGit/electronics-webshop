package pl.connectis.electronicswebshop.products;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.connectis.electronicswebshop.service.ProductService;

import java.security.Principal;


@Controller
public class ProductsController {
    private final ProductsRepository productsRepository;

    private final ProductService productService;

    public ProductsController(ProductsRepository productsRepository, ProductService productService) {
        this.productsRepository = productsRepository;
        this.productService = productService;
    }

//@PostMapping("/order/{productName}")
//public Order newOrder (@PathVariable String productName) {
//   return orderRepository.createOrder(new Product(productName));
//}

    //Czynności po stronie Pracownika

    @GetMapping(value = "/")
    public String viewAllProducts(Model model) {
        Iterable<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }


    @PostMapping("/")
    public String addToOrder(
            @RequestParam(value = "quantity", required = false) String quantity,
            @RequestParam(value = "productID", required = false) String productID,
            Model model
    ) {

        return "index";
    }

    @GetMapping("/addProduct/{productName}")
    @ResponseBody
    public Product addProduct(@PathVariable String productName, Principal principal) {
        Product prod = new Product(productName, principal.getName());
        return productsRepository.save(prod);
    }

}



