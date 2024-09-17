package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sisnot.sisnot.Model.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
