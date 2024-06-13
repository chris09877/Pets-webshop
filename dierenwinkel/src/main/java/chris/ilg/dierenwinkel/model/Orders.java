package chris.ilg.dierenwinkel.model;

import chris.ilg.dierenwinkel.service.OrdersDto;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orders {


    public Orders() {
    }

    public Orders(OrdersDto ordersDto, User user) {
        this.delivery_information = ordersDto.getDelivery_information();
        this.date = ordersDto.getDate();
        this.orderProducts = new HashSet<>(); // Initialize the set
        /*this.orderProducts.add(op);*/ //        this.userInfo = ordersDto.getUserInfo();
        //this.orderProducts.add(new OrderProductServiceImpl().create(ordersDto.getOrderProductsDto()));
        this.user = user;
        this.userInfo = ordersDto.getUserInfo();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> orderProducts = new HashSet<>();
    @Column(nullable = true)
    private String delivery_information;
    @Column(nullable = false, length = 100)
    private Date date;

    @Column(nullable = false)
    private String userInfo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDelivery_information() {
        return delivery_information;
    }

    public void setDelivery_information(String delivery_information) {
        this.delivery_information = delivery_information;
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


    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}