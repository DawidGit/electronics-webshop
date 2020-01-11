package pl.connectis.electronicswebshop.products;

import pl.connectis.electronicswebshop.order.ProductQuantity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "productID")
    public Long productID;
    @Column(name = "productName")
    public String productName;
    @Column(name = "stock")
    public int stock;
    @Column(name = "addedBy")
    private String addedBy;

    @OneToMany(mappedBy = "product")
    private Collection<ProductQuantity> quantities;

    public Product() {
    }

    public Product(String productName, String addedBy) {
        this.productName = productName;
        this.addedBy = addedBy;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

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
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Collection<ProductQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Collection<ProductQuantity> quantities) {
        this.quantities = quantities;
    }

}
