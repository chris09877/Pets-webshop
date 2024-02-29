package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Order;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }
}
