package chris.ilg.dierenwinkel.model;

import chris.ilg.dierenwinkel.controller.UserController;
import chris.ilg.dierenwinkel.repository.UserRepo;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.OrdersDto;
import chris.ilg.dierenwinkel.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Orders {


    public Orders() {}

    public Orders(OrdersDto ordersDto, User user/*, OrderProduct op*/) {
        this.content = ordersDto.getContent();
        this.date = ordersDto.getDate();
        this.orderProducts = new HashSet<>(); // Initialize the set
        /*this.orderProducts.add(op);*/ //        this.userInfo = ordersDto.getUserInfo();
        this.user = user;
        this.userInfo = ordersDto.getUserInfo();
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "order_product",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//   //@JsonManagedReference
//    private Set<Product> products = new HashSet<>();
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<OrderProduct> orderProducts = new HashSet<>();
    @Column(nullable = true)
    //@ElementCollection
    private String content;
    @Column(nullable = false, length = 100)
    private Date date;

    @Column(nullable = true)
    private String userInfo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}