package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "componente1_nota")
    private Double componente1Nota;

    @Column(name = "componente2_nota")
    private Double componente2Nota;

    @Column(name = "nota_parcial")
    private Double notaParcial;

    @Column(name = "componente3_nota")
    private Double componente3Nota;

    @Column(name = "componente4_nota")
    private Double componente4Nota;

   @Column(name = "nota_final")
   private Double notaFinal;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_fk",  foreignKey = @ForeignKey(name = "fk_nota_alumnoid"))
    private Alumno alumnofk;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_fk", foreignKey = @ForeignKey(name = "fk_nota_cursoid"))
    private Curso cursofk;

}
