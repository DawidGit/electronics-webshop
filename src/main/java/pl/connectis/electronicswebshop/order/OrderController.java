package pl.connectis.electronicswebshop.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.connectis.electronicswebshop.products.Product;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/allorders")
    public String viewAllOrders(Model model) {
        Iterable<Order> listOrders = orderService.getAllOrders();
        model.addAttribute("listOrders", listOrders);
        return "allOrdersView";
    }


    @GetMapping("/deleteOrder")
    public String viewDeleteConfirmation(Model model, Order order, Principal principal, OrderStatus orderStatus) {
        orderService.deleteOrder(principal, orderStatus);
        return "deleteOrderConfirmationView";
    }


    @GetMapping("/addorder")

    public String addedOrder(Model model) {
        model.addAttribute("order", orderService.addOrder());
        return "addedOrderView";
    }

    @GetMapping("order/{orderID}/allproducts")
    public List<Product> getAllProductsFromOrder(@PathVariable String orderID) {
        return null;
    }


    @GetMapping("/basket")
    public String showBasket(Model model, Principal principal, Order order) {
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        Order foundOrder = orderService.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);


        if (foundOrder != null) {

            List<OrderLine> productsList = new ArrayList<>(foundOrder.getOrderLines());
            model.addAttribute("productsList", productsList);
            model.addAttribute("order", foundOrder);
        } else {
            model.addAttribute("order", order);
        }


        return "basketView";
    }

    @PostMapping("/basket")
    public String deleteArticleFromOrder(
            @RequestParam(value = "quantity") int quantity,
            @RequestParam(value = "productId") Long productID,
            @RequestParam(value = "orderId") Long orderID,
            Principal principal,
            Model model
    ) {
        Order order = orderService.removeLine(orderID, productID, quantity);
        return showBasket(model, principal, order);
    }

}



