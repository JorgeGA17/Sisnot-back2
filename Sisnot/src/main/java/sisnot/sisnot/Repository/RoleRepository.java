package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisnot.sisnot.Model.entity.Role;
import sisnot.sisnot.Model.enums.ERole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

   Optional<Role> findByName(ERole name);
}
