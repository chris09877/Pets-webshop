package chris.ilg.dierenwinkel.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String name,description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;
}
