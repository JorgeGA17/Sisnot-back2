package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sisnot.sisnot.Model.entity.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {
}
