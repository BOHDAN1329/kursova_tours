package applica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import applica.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}