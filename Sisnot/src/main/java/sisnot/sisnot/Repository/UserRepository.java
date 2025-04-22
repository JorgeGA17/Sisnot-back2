package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisnot.sisnot.Model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
