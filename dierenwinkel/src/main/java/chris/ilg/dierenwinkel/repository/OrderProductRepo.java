package chris.ilg.dierenwinkel.repository;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Integer> {

    OrderProduct save(OrderProduct op);

    List<OrderProduct> findAllByOrderId(int orderId);

}
