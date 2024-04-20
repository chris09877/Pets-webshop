package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class OrdersDto {

    private int id;
    private int userId;
    private Set<Product> products;
    private String content;
    private Date date;
    private String userInfo;

    public OrdersDto() {
    }

    public OrdersDto(Orders o) {
        this.id = o.getId();
        this.userId = o.getUser().getId();
        this.products = o.getProducts();
        this.content = o.getContent();
        this.date = o.getDate();
        this.userInfo = o.getUserInfo();
    }
    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
}
