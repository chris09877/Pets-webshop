package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;

public class ProductDto {

    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int categoryId;

    public ProductDto() {
    }

    public ProductDto(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.quantity = p.getQuantity();
        this.categoryId = p.getCategories().getValue();
    }

    public ProductDto(Product p, Orders o) {
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.quantity = p.getQuantity();
        this.categoryId = p.getCategories().getValue();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
