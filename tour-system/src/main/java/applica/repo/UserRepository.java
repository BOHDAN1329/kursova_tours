package applica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import applica.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}