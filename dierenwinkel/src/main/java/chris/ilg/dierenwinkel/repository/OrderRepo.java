package chris.ilg.dierenwinkel.repository;

import chris.ilg.dierenwinkel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {


}
