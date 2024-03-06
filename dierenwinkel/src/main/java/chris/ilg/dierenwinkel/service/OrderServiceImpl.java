package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Orders saveOrder(Orders order) {
        return orderRepo.save(order);
    }
}