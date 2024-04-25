package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.OrderProduct;

import java.util.List;

public interface OrderProductService {

    public OrderProduct create(OrderProductDto opd);

    public List<OrderProduct> getAllByOrderId(int id);
}
