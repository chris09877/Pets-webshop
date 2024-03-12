package chris.ilg.dierenwinkel.model;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders = new ArrayList<Orders>();
    @Column(nullable = false, length = 255)
    private String name,description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;

    public enum Category {
        FISH(1),
        DOG(2),
        CAT(3);

        private final int value;

        Category(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Category categories;


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

    public int getId() {
        return id;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}