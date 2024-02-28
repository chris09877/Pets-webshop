package chris.ilg.dierenwinkel.repository;

import chris.ilg.dierenwinkel.model.Order;
import chris.ilg.dierenwinkel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
