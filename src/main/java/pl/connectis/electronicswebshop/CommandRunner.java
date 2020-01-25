package pl.connectis.electronicswebshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.electronicswebshop.order.Order;
import pl.connectis.electronicswebshop.order.OrderService;
import pl.connectis.electronicswebshop.order.OrderStatus;
import pl.connectis.electronicswebshop.order.ProductQuantity;
import pl.connectis.electronicswebshop.products.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
public class CommandRunner implements CommandLineRunner {


    @Autowired
    private OrderService orderService;


    @Override
    public void run(String... args) throws Exception {


        Order foundOrder = orderService.findByAddedByAndOrderStatus("Admin", OrderStatus.OPEN);

        System.out.println("#############################################################################");

        if (foundOrder != null) {
            List<Product> productsList = new ArrayList<>();
            Collection<ProductQuantity> products = foundOrder.getProducts();


            System.out.println(products);


        }

        System.out.println("#############################################################################");
    }

}
