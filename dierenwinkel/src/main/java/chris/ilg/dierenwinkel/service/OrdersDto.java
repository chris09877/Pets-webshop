package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdersDto {
    private List<ProductDto2> products;

    private int id;
    private int userId;
    private Set<OrderProduct> orderProducts = new HashSet<>();
    private String content;
    private Date date;
    private String userInfo;

    public OrdersDto() {
    }

    public OrdersDto(Orders o,OrderProduct op) {
        this.id = o.getId();
        this.userId = o.getUser().getId();
        //this.products = o.getProducts();
        this.orderProducts = new HashSet<>(); // Initialize the set
        // Assuming you have a DTO conversion for OrderProduct
        this.orderProducts.add(op); //        this.content = o.getContent();
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

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
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

    public List<ProductDto2> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto2> products) {
        this.products = products;
    }

    public  class ProductDto2 {
        private int orderId, productId;
        private double price;
        private int quantity;
        private String name;
        private double total;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }


}





