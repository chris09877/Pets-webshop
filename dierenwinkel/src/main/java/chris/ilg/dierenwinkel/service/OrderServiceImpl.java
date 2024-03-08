package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Orders saveOrder(Orders order) {
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
    public Orders updateOrder(String userInfo, Orders updatedOrder) {
        Orders existingOrder = orderRepo.findByUserInfo(userInfo);

        if (existingOrder != null) {
            // Update the necessary fields
            existingOrder.setDate(updatedOrder.getDate());
            existingOrder.setContent(updatedOrder.getContent());

            return orderRepo.save(existingOrder);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found for userInfo: " + userInfo);
        }
    }
}