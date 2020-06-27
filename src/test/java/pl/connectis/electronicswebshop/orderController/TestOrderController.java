package pl.connectis.electronicswebshop.orderController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.connectis.electronicswebshop.InitialDataLoader;
import pl.connectis.electronicswebshop.order.OrderService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TestOrderController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;


    @Autowired
    public InitialDataLoader initialDataLoader;


    @Test
    public void urlControllerTest() throws Exception {

        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Hello anon!")));

        mockMvc
                .perform(get("/allorders"))
                .andExpect(status().isOk())
                .andExpect(view().name("allOrdersView"))
                .andExpect(content().string(containsString("Order List")));

        mockMvc
                .perform(get("/deleteOrder"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderLackView"))
                .andExpect(content().string(containsString("There is not any order")));

        mockMvc
                .perform(get("/addorder"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New Order Created!")));

        mockMvc

                .perform(get("/basket"))
                .andExpect(status().isOk())
                .andExpect(view().name("basketView"))
                .andExpect(content().string(containsString("Products:")));
    }
}


