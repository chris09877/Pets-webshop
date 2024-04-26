package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.repository.OrderProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepo orderProductRepo;
    //    @Autowired
//    private OrderServiceImpl orderServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;


    @Override
    public OrderProduct create(OrderProductDto opd) {

//       Orders o =  orderServiceImpl.getOrderById(opd.getOrderId());
//       if (o == null){
//           System.out.println("No order found with matching id:" + opd.getOrderId());
//       }
//        Product p = productServiceImpl.getProductById(opd.getProductId());
//        if (p == null){
//            System.out.println("No product found with matching id:" + opd.getProductId());
//        }
        OrderProduct op = new OrderProduct(opd);
        return orderProductRepo.save(op);
    }

    @Override
    public List<OrderProduct> getAllByOrderId(int id) {
        List<OrderProduct> listOP = orderProductRepo.findAllByOrderId(id);
        if (listOP.isEmpty()) {
            System.out.println("No order products found for this id:" + id);
        }

        return listOP;
    }
}
