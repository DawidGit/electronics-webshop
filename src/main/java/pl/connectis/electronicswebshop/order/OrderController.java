package pl.connectis.electronicswebshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.service.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    //private final OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

//    public OrderController(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    //@PostMapping("/order/{productName}")
//public Order newOrder (@PathVariable String productName) {
//   return orderRepository.createOrder(new Product(productName));
//}
//    @GetMapping("/order")
//    public Order createOrder() {
//        return orderRepository.save(new Order());
//    }

    @GetMapping(value = "/allorders")
    public String viewAllOrders(Model model){
        Iterable<Order> listOrders = orderService.getAllOrders();
        model.addAttribute("listOrders", listOrders);
        return "allOrdersView";
    }


    @GetMapping("/addorder")

    public String addedOrder(Model model) {


        model.addAttribute("order",orderService.addOrder());
        return "addedOrderView";
    }

    @GetMapping("order/{orderID}/allproducts")
    public List<Product> getAllProductsFromOrder (int orderID){
        return null;
    }

}

//    public @ResponseBody Iterable<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    @GetMapping("/order/{orderID}")
//    public @ResponseBody Order getOrderByNo(@PathVariable int orderID) {
//        return orderRepository.findByOrderID(orderID);
//    }
//
//    @GetMapping("/order/remove/{orderID}")
//    public void removeOrderByNo(@PathVariable int orderID) {
//        orderRepository.delete(orderRepository.findByOrderID(orderID));
//    }
//
//
//    @GetMapping(value = "/order")
//    public @ResponseBody  ModelAndView showForm() {
//        return new ModelAndView("orderHome", "order", new Order());
//    }
//
//    @PostMapping(value = "/addorder")
//    public String viewAddedOrder(@Valid @ModelAttribute("order")Order order,
//                         BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//           return "error";
//        }
//        orderRepository.save(order);
//        model.addAttribute("id: ", order.getOrderID());
//        return "orderResult";
//    }



