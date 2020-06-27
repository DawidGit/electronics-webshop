package pl.connectis.electronicswebshop.products;


import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderService;
import pl.connectis.electronicswebshop.order.OrderStatus;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@ControllerAdvice
public class ProductsController {
    private final OrderService orderService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private final ProductsRepository productsRepository;
    private final ProductService productService;

    public ProductsController(OrderService orderService, ProductsRepository productsRepository, ProductService productService) {
        this.orderService = orderService;
        this.productsRepository = productsRepository;
        this.productService = productService;
    }

    private String indexView(Model model) {
        Iterable<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @ModelAttribute
    private void basketCountView(Model model, Principal principal) {
        String username = (((principal == null) || (principal.getName() == null)) ? "Anonymous" : principal.getName());
        Order foundOrder = orderService.findByAddedByAndOrderStatus(username, OrderStatus.OPEN);
        model.addAttribute("basketCount", ((foundOrder == null) || (foundOrder.getProducts() == null)) ? 0 : foundOrder.getProducts().size());
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
            Principal principal) {
        if (quantity < 0) return "error1";
        Product product = productService.getProductByID(productID);
        if (product == null) return "error1";
        if (product.getStock() < quantity) return "error1";
        String username = ((principal == null) ? "Anonymous" : principal.getName());
        if (!orderService.addProductToOrder(product, quantity, username)) return "error1";
        basketCountView(model, principal);
        return indexView(model);
    }


    @GetMapping("/addProduct")
    public String ProductsView(Model model) {

        model.addAttribute("allProducts", productsRepository.findAll());
        return "productsView";
    }

    @GetMapping("/addProduct/new")
    public String addNewProductForm(Model model, ProductDto productDto) {

        model.addAttribute("product", productDto);
        return "addProduct";
    }


    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") @Valid ProductDto productDto, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT HAS ERRORS");
            return "addProduct";
        } else {
            productService.addProduct(productDto);
            model.addAttribute("allProducts", productsRepository.findAll());

            return "productsView";
        }
    }
}



