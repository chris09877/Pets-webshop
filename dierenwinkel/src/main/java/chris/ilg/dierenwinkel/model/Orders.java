package chris.ilg.dierenwinkel.model;

import chris.ilg.dierenwinkel.controller.UserController;
import chris.ilg.dierenwinkel.repository.UserRepo;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.OrdersDto;
import chris.ilg.dierenwinkel.service.UserServiceImpl;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
public class Orders {


    public Orders() {}

//    public Orders(OrdersDto ordersDto) {
//        // Initialize Orders object using data from OrdersDto
//        this.content = ordersDto.getContent();
//        this.date = ordersDto.getDate();
//        this.products = ordersDto.getProducts();
//        this.userInfo = ordersDto.getUserInfo();
//        this.user = UserServiceImpl.getUserByID(ordersDto.getUserId());
//
//    }
    public Orders(OrdersDto ordersDto, User user) {
        this.content = ordersDto.getContent();
        this.date = ordersDto.getDate();
        this.products = ordersDto.getProducts();
        this.userInfo = ordersDto.getUserInfo();
        this.user = user;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<Product>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}