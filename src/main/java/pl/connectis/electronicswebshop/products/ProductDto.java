package pl.connectis.electronicswebshop.products;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Data
public class ProductDto {

    @NotEmpty(message = "Nie może być puste")
    private String productName;

    @NotNull
    @Min(value = 1, message = "Wartość musi być min 1")
    private int stock;

    private String addedBy;

    public String getAddedBy(Principal principal) {
        this.addedBy = principal.getName();
        return this.addedBy;
    }

}
