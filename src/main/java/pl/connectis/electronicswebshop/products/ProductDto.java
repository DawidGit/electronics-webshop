package pl.connectis.electronicswebshop.products;

import pl.connectis.electronicswebshop.persistence.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;

public class ProductDto {



    @NotNull
    @NotEmpty
    public String productName;

    @NotNull
    @NotEmpty
    public int quantity;


    @NotNull
    @NotEmpty
    public String addedBy;

    private User user;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddedBy() {
        this.addedBy = user.getFirstName();
        return this.addedBy;
    }

}
