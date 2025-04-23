package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sisnot.sisnot.Model.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {


}
