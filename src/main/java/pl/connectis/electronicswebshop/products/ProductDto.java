package pl.connectis.electronicswebshop.products;

import pl.connectis.electronicswebshop.persistence.model.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;


public class ProductDto {

    @NotEmpty(message = "Nie może być puste")
    public String productName;

    @NotNull
    @Min(value = 1, message = "Wartość musi być min 1")
    public int stock;


    public String addedBy;

    private User user;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getAddedBy(Principal principal) {
        this.addedBy = principal.getName();
        return this.addedBy;
    }

}
