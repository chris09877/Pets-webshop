package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import org.hibernate.query.Order;

import java.util.ArrayList;

public interface OrderService {
    public Orders saveOrder(Orders order);
    public ArrayList<Orders> getAllOrders();
    public Orders getOrderById(int id);
    public Orders updateOrder(String userInfo, Orders updatedOrder);
}