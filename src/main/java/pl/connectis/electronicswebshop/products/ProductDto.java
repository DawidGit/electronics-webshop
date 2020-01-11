package pl.connectis.electronicswebshop.products;

import pl.connectis.electronicswebshop.persistence.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    @NotEmpty
    public String productName;

    @NotNull
    @NotEmpty
    public int stock;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getAddedBy() {
        this.addedBy = user.getFirstName();
        return this.addedBy;
    }

}
