package chris.ilg.dierenwinkel.model;

import chris.ilg.dierenwinkel.repository.OrderProductRepo;
import chris.ilg.dierenwinkel.service.OrderProductDto;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.ProductServiceImpl;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "order_product")
public class OrderProduct {


    public OrderProduct() {
    }

    public OrderProduct(OrderProductDto opd) {
        this.order = new OrderServiceImpl().getOrderById(opd.getOrderId());
        ;
        this.product = new ProductServiceImpl().getProductById(opd.getProductId());
        this.quantity = opd.getQuantity();
        this.price = opd.getPrice();
        this.total = opd.getTotal();
        this.name = opd.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price, total;

    @Column(nullable = false)
    private String name;

    private int productId, orderId;

    public void setTotal(double total) {
        this.total = total;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = product.getId();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = order.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
