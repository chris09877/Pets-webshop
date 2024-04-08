package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import org.hibernate.query.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    public Orders saveOrder(OrdersDto ordersDto);
    public ArrayList<Orders> getAllOrders();
    public Orders getOrderById(int id);
    public Orders updateOrder(String userInfo, OrdersDto updatedOrderDto);
    public Orders getOrderByUserInfo(String userInfo);
    public void deleteOrder(int id);
    public List<Orders> getAllOrdersById(List<Integer> ids);


}