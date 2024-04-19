package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{


    public OrderServiceImpl() {
    }


@Autowired
private UserServiceImpl userServiceImpl;

    @Autowired
    public OrderRepo orderRepo;
    @Override
    public Orders saveOrder(OrdersDto ordersDto) {
        User user = userServiceImpl.getUserById(ordersDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");  // Or handle this case as needed
        }
        Orders order = new Orders(ordersDto, user);
        return orderRepo.save(order);

    }

    @Override
    public ArrayList<Orders> getAllOrders() {
        ArrayList<Orders> listOfOrders = new ArrayList<>();
        listOfOrders.addAll(orderRepo.findAll());
        return listOfOrders;
    }

    @Override
    public Orders getOrderById(int id) {
        return orderRepo.getById(id);
    }

    @Override
    @Transactional
    public Orders updateOrder(Integer userId, OrdersDto updatedOrderDto) {
        Orders existingOrder = orderRepo.findByUserId(userId);

        if (existingOrder != null) {
            // Update the necessary fields
            existingOrder.setDate(updatedOrderDto.getDate());
            existingOrder.setContent(updatedOrderDto.getContent());
            existingOrder.setUserInfo(updatedOrderDto.getUserInfo());
            // existingOrder.setUser(updatedOrderDto.getUser());//pas très sur que ca va marcher


            return orderRepo.save(existingOrder);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found for userInfo: " + userId);
        }
    }

    @Override
    public Orders getOrderByUserId(Integer userId) {
        Orders existingOrder = orderRepo.findByUserId(userId);
        if (existingOrder != null) {
            return existingOrder;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found for userInfo: " + userId);
        }
    }

    @Override
    public void deleteOrder(int id) {

        if (orderRepo.existsById(id)) {
            // Delete the order
            orderRepo.deleteById(id);
        } else {
            // If the order does not exist, throw a not found exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + id);
        }
    }
    public ArrayList<Orders> getAllOrdersById(List<Integer> ids){
        if (ids != null ) {
            List<Orders> ordersList = orderRepo.findAllById(ids);
            ArrayList<Orders> ordersArray = new ArrayList<>();
            ordersList.forEach(order -> ordersArray.add(order));
            return ordersArray;
        }
        ArrayList<Orders> orders = new ArrayList<>();
        return orders;
    }

    @Override
    public Orders getOrderByUserInfo(String sessionId) {
        Orders order = orderRepo.findByUserInfo(sessionId);
        return order;
    }
}