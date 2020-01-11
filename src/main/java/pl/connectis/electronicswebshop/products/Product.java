package pl.connectis.electronicswebshop.products;

import pl.connectis.electronicswebshop.persistence.model.User;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "productID")
    public int productID;
    @Column(name = "productName")
    public String productName;
    @Column(name = "quantity")
    public int quantity;
    @Column (name = "addedBy")
    private final String addedBy;



    public Product() {
        this.productName = productName;
        User user = new User();
        this.addedBy = user.getUsername();
    }

    public Product(String productName, String username, String addedBy) {
        this.addedBy = addedBy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAddedBy() {

        return this.addedBy;
    }


    //skąd wziąć zalogowanego użytkoiwnika?

}
