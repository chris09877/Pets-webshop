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

    private OrderProductDto orderProductsDto;

    private String delivery_information;
    private Date date;
    private String userInfo;

    public OrdersDto() {
    }


    public OrdersDto(int id, int userId, OrderProductDto opd, String delivery_information, Date date, String userInfo) {
        this.id = id;
        this.userId = userId;
        this.orderProductsDto = opd;
        this.delivery_information = delivery_information;
        this.date = date;
        this.userInfo = userInfo;
    }

    //DTO TO SEND TO FRONTEND
    public OrdersDto(Orders o, OrderProduct op) {
        this.id = o.getId();
        this.userId = o.getUser().getId();
        this.orderProducts = new HashSet<>();
        this.orderProducts.add(op);
        this.date = o.getDate();
        this.userInfo = o.getUserInfo();
    }

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

    public List<ProductDto2> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto2> products) {
        this.products = products;
    }

    public OrderProductDto getOrderProductsDto() {
        return orderProductsDto;
    }

    public void setOrderProductsDto(OrderProductDto orderProductsDto) {
        this.orderProductsDto = orderProductsDto;
    }

    public class ProductDto2 {
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





