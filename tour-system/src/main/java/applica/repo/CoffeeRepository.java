package applica.repo;

import applica.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Tour, Long> {

}