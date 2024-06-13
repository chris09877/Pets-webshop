package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.OrderProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private ProductServiceImpl productServiceImpl;


    @Override
    public OrderProduct create(OrderProductDto opd, Product p, Orders o) {
        OrderProduct op = new OrderProduct(opd,p,o);
        return orderProductRepo.save(op);
    }

    @Override
    public List<OrderProductDto> getAllByOrderId(int id) {
        List<OrderProduct> listOP = orderProductRepo.findAllByOrderId(id);
        if (listOP.isEmpty()) {
            System.out.println("No order products found for this id:" + id);
        }
        List<OrderProductDto> listOPD = new ArrayList<>();
        listOP.forEach(orderProduct -> {
            OrderProductDto opd = new OrderProductDto(orderProduct.getOrder().getId(),orderProduct.getProduct().getId(),orderProduct.getQuantity(),orderProduct.getPrice(),orderProduct.getTotal(),orderProduct.getName());
            listOPD.add(opd);
        });

        return listOPD;
    }
}
