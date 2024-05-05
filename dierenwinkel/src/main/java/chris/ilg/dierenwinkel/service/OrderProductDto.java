package chris.ilg.dierenwinkel.service;

public class OrderProductDto {

    private int productId, orderId;
    private int quantity;
    private double price;
    private double total;
    private String name;

    // Constructors
    public OrderProductDto() {
    }

    public OrderProductDto(int orderId, int productId, int quantity, double price, double total, String name) {

        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
