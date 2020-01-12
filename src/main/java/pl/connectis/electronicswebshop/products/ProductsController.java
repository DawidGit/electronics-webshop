package pl.connectis.electronicswebshop.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderStatus;
import pl.connectis.electronicswebshop.order.ProductQuantity;
import pl.connectis.electronicswebshop.service.OrderService;
import pl.connectis.electronicswebshop.service.ProductService;

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


        Product product = productsRepository.findById(productID).get();
        if (product.getStock() < quantity) return "error";
        username = ((principal != null) ? principal.getName() : "Anonymous");

        if (!validateProduct(product, quantity, username)) return "error";

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

    private boolean validateProduct(Product product, int quantity, String username) {

        ProductQuantity productQuantity = null;
        Iterable<Order> userOrders = orderService.findAllByAddedBy(username);
        Order lastOpenOrder = null;
        for (Order userOrder : userOrders) {
            if (userOrder.getOrderStatus() == OrderStatus.OPEN) {
                lastOpenOrder = userOrder;
                for (ProductQuantity products : orderService.findAllProductsByOrder(lastOpenOrder)) {
                    if (products.getProduct().equals(product)) {
                        productQuantity = products;
                        productQuantity.setQuantity(productQuantity.getQuantity() + quantity);
                    }
                }
            }
        }
        if (lastOpenOrder == null) {
            lastOpenOrder = orderService.addOrder(username);
        }
        if (productQuantity == null) {
            productQuantity = new ProductQuantity(lastOpenOrder, product, quantity);
            lastOpenOrder.getProducts().add(productQuantity);
        }
        orderService.saveOrder(lastOpenOrder);
        return true;
    }

}



