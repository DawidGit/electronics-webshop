package pl.connectis.electronicswebshop.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderService;
import pl.connectis.electronicswebshop.order.OrderStatus;

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

    private String indexView(Model model, String username) {
        Iterable<Product> listProducts = productService.getAllProducts();
        Order foundOrder = orderService.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        int basketCount = ((foundOrder == null) ? 0 : foundOrder.getProducts().size());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("basketCount", basketCount);

        return "index";
    }

    @GetMapping({"/", "/home"})
    public String viewAllProducts(Model model, Principal principal) {
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        return indexView(model, username);
    }


    @PostMapping("/")
    public String addToOrder(
            @RequestParam(value = "quantity", required = false) int quantity,
            @RequestParam(value = "id", required = false) Long productID,
            Model model,
            Principal principal
    ) {
        if (quantity < 0) return "error";
        Product product = productService.getProductByID(productID);
        if (product == null) return "error";
        if (product.getStock() < quantity) return "error";
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        if (!orderService.addProductToOrder(product, quantity, username)) return "error";

        return indexView(model, username);
    }


    @GetMapping("/addProduct/{productName}")
    @ResponseBody
    public Product addProduct(@PathVariable String productName, Principal principal) {
        Product prod = new Product(productName, principal.getName());
        productService.addProduct(prod);
        return prod;
    }

}



