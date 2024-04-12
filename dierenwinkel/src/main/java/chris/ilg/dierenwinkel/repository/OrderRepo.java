package chris.ilg.dierenwinkel.repository;

import chris.ilg.dierenwinkel.model.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {


        Orders findByUserId(Integer userId);
        List<Orders> findAllById(Iterable<Integer> ids);

}