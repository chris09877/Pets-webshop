package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.OrderProductRepo;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    OrderProductServiceImpl orderProductService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    public OrderRepo orderRepo;
    @Override
    public void saveOrder(OrdersDto ordersDto) {
        User user = userServiceImpl.getUserById(ordersDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");  // Or handle this case as needed
        }

        Orders order = new Orders(ordersDto, user);
        Orders savedOrder = orderRepo.save(order);
        if (savedOrder == null){
            System.out.println("The order wasn't saved");
        }
        for ( OrdersDto.ProductDto2 productDto : ordersDto.getProducts()) {

            Product p = productServiceImpl.getProductById(productDto.getProductId());

            OrderProductDto opd = new OrderProductDto(savedOrder.getId(),productDto.getProductId(),productDto.getQuantity(),productDto.getTotal(),productDto.getPrice(),productDto.getName());
            opd.setOrderId(savedOrder.getId());
            OrderProduct op = new OrderProduct(opd);
            orderProductService.create(opd);
        }

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
    public Orders updateOrder(String userInfo, OrdersDto updatedOrderDto) {

//        Orders existingOrder = orderRepo.findByUserInfo(userInfo);
//
//        if (existingOrder != null) {
//            // Update the necessary fields
//            existingOrder.getProducts().addAll(updatedOrderDto.getProducts());
//            // existingOrder.setUser(updatedOrderDto.getUser());//pas très sur que ca va marcher
//
//
//            return orderRepo.save(existingOrder);
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found for userInfo: " + userInfo);
//        }
        return null;
    }

    @Override
    public Orders getOrderByUserId(Integer userId) {
        Orders existingOrder = orderRepo.findByUserId(userId);
        if (existingOrder != null) {
            return existingOrder;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found for user id: " + userId);
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

    public ArrayList<Orders> getAllOrdersByUserId(Integer id){

            List<Orders> ordersList = orderRepo.findAllByUserId(id);
            ArrayList<Orders> ordersArray = new ArrayList<>();
            ordersList.forEach(order -> ordersArray.add(order));
            return ordersArray;

    }
}