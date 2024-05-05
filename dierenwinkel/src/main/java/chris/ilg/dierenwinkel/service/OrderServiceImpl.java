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
public class OrderServiceImpl implements OrderService {


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
        // Check for a valid user
        User user = userServiceImpl.getUserById(ordersDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");  // Or handle this case as needed
        }
        System.out.println("order dto: "+ ordersDto);
        Orders order = new Orders(ordersDto, user);
        Orders savedOrder = orderRepo.save(order);

        // Ensure that OrderProductsDto is not null
        if (ordersDto.getOrderProductsDto() != null) {
            ordersDto.getOrderProductsDto().setOrderId(savedOrder.getId());

            Product product = productServiceImpl.getProductById(ordersDto.getOrderProductsDto().getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found");  // Or handle as needed
            }

            OrderProduct op = orderProductService.create(ordersDto.getOrderProductsDto(), product, savedOrder);
            savedOrder.getOrderProducts().add(op);
            orderRepo.save(savedOrder);
        } else {
            throw new IllegalArgumentException("Order products details are missing");
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
        return null;
    }

    @Override
    public Orders getOrderByUserId(Integer userId) {
        Orders existingOrder = orderRepo.findByUserId(userId);
        if (existingOrder != null) {
            return existingOrder;
        } else {
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

    public ArrayList<Orders> getAllOrdersById(List<Integer> ids) {
        if (ids != null) {
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

    public ArrayList<Orders> getAllOrdersByUserId(Integer id) {

        List<Orders> ordersList = orderRepo.findAllByUserId(id);
        ArrayList<Orders> ordersArray = new ArrayList<>();
        ordersList.forEach(order -> ordersArray.add(order));
        return ordersArray;

    }
}