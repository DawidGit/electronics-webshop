package pl.connectis.electronicswebshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectis.electronicswebshop.products.Product;
import pl.connectis.electronicswebshop.products.ProductService;
import pl.connectis.electronicswebshop.products.ProductsRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    //private final OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsRepository productsRepository;

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
        model.addAttribute("order", orderService.addOrder());
        return "addedOrderView";
    }

    @GetMapping("order/{orderID}/allproducts")
    public List<Product> getAllProductsFromOrder(int orderID) {
        return null;
    }


    @GetMapping(value = "/basket")
    public String showBasket(Model model, Principal principal, Order order) {
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        Order foundOrder = orderService.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);


        if (foundOrder != null) {
            List<Product> productsList = new ArrayList<>();

            for (ProductQuantity product : foundOrder.getProducts()) {
                Product foundedProduct = product.getProduct();
                productsList.add(foundedProduct);
            }

            model.addAttribute("productsList", productsList);
            //model.addAttribute("productsList", productsRepository.findAllProductsByOrdersId(order.getId()));
            model.addAttribute("order", foundOrder);
        } else {
            model.addAttribute("order", order);
        }
        return "basketView";
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
//        model.addAttribute("id: ", order.getId());
//        return "orderResult";
//    }



