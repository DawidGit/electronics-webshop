package pl.connectis.electronicswebshop.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.connectis.electronicswebshop.order.OrderService;

import java.security.Principal;


@Controller
public class ProductsController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductService productService;


//@PostMapping("/order/{productName}")
//public Order newOrder (@PathVariable String productName) {
//   return orderRepository.createOrder(new Product(productName));
//}

    //Czynności po stronie Pracownika

    @GetMapping({"/", "/home"})
    public String viewAllProducts(Model model) {
        Iterable<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }


    @PostMapping("/")
    public String addToOrder(
            @RequestParam(value = "quantity", required = false) int quantity,
            @RequestParam(value = "id", required = false) Long productID,
            String username,
            Model model,
            Principal principal
    ) {
        if (quantity < 0) return "error";
        Product product = productService.getProductByID(productID);
        if (product == null) return "error";
        if (product.getStock() < quantity) return "error";
        username = ((principal == null) ? "Anonymous" : principal.getName());
        if (!orderService.addProductToOrder(product, quantity, username)) return "error";

        Iterable<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/addProduct/{productName}")
    @ResponseBody
    public Product addProduct(@PathVariable String productName, Principal principal) {
        Product prod = new Product(productName, principal.getName());
        productService.addProduct(prod);
        return prod;
    }

}



