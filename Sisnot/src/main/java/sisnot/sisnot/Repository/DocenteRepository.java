package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sisnot.sisnot.Model.entity.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

}
