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
@ControllerAdvice
public class ProductsController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductService productService;

    private String indexView(Model model) {
        Iterable<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @ModelAttribute
    private void basketCountView(Model model, Principal principal) {
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        Order foundOrder = orderService.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        model.addAttribute("basketCount", (foundOrder == null) ? 0 : foundOrder.getProducts().size());
    }

    @GetMapping({"/", "/home"})
    public String viewAllProducts(Model model) {
        return indexView(model);
    }


    @PostMapping("/")
    public String addToOrder(
            @RequestParam(value = "quantity", required = false) int quantity,
            @RequestParam(value = "id", required = false) Long productID,
            Model model,
            Principal principal
    ) {
        if (quantity < 0) return "error1";
        Product product = productService.getProductByID(productID);
        if (product == null) return "error1";
        if (product.getStock() < quantity) return "error1";
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        if (!orderService.addProductToOrder(product, quantity, username)) return "error1";
        basketCountView(model, principal);
        return indexView(model);
    }


    @GetMapping("/addProduct/{productName}")
    @ResponseBody
    public Product addProduct(@PathVariable String productName, Principal principal) {
        Product prod = new Product(productName, principal.getName());
        productService.addProduct(prod);
        return prod;
    }

}



