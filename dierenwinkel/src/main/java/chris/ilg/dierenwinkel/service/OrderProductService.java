package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;

import java.util.List;

public interface OrderProductService {

    public OrderProduct create(OrderProductDto opd, Product p, Orders o);

    public List<OrderProductDto> getAllByOrderId(int id);
}
