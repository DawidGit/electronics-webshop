package pl.connectis.electronicswebshop.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductDto {

    @NonNull
    @NotEmpty(message = "Nie może być puste")
    private String productName;

    @NonNull
    @NotNull
    @Min(value = 1, message = "Wartość musi być min 1")
    private int stock;

    @NonNull
    private String addedBy;


}
