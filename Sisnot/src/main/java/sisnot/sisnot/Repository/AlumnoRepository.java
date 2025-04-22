package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sisnot.sisnot.Model.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    boolean existsBynombreAndAndApellidoPaterno (String nombre, String apellidoPaterno);

    boolean existsBynombreAndAndApellidoPaternoAndUserIdNot (String nombre, String apellidoPaterno, Integer userId);
}
