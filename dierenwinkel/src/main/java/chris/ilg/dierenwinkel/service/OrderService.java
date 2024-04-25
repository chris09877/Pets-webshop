package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    public void saveOrder(OrdersDto ordersDto);
    public ArrayList<Orders> getAllOrders();
    public Orders getOrderById(int id);
    public Orders updateOrder(String userInfo, OrdersDto updatedOrderDto);
    public Orders getOrderByUserId(Integer userId);
    public void deleteOrder(int id);
    public List<Orders> getAllOrdersById(List<Integer> ids);
    public Orders getOrderByUserInfo(String sessionId);
    public List<Orders> getAllOrdersByUserId(Integer id);


}