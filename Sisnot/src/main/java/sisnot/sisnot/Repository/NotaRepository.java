package sisnot.sisnot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Model.entity.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Nota n SET n.componente1Nota = :componente1Nota, " +
            "n.componente2Nota = :componente2Nota, " +
            "n.componente3Nota = :componente3Nota, " +
            "n.componente4Nota = :componente4Nota, " +
            "n.notaParcial = 0.25 * :componente1Nota + 0.2 * :componente2Nota, " +
            "n.notaFinal = 0.25 * :componente3Nota + 0.3 * :componente4Nota, " +
            "n.promedioFinal = 0.25 * :componente1Nota + 0.2 * :componente2Nota + " +
            "0.25 * :componente3Nota + 0.3 * :componente4Nota " +
            "WHERE n.alumnofk.id = :alumnoId AND n.cursofk.id = :cursoId")

    void updateNotasByAlumnoId(
            Double componente1Nota,
            Double componente2Nota,
            Double componente3Nota,
            Double componente4Nota,
            Long alumnoId,
            Long cursoId  // Nuevo parámetro para el ID del curso
    );
}
