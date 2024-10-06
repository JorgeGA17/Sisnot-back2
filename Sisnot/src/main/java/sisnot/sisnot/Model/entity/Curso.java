package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "nom_curso", length = 100, unique = true)
    private String nomCurso;

    @Size(max = 30)
    @Column(name = "ciclo", length = 30)
    private String ciclo;

    @Size(max = 30)
    @Column(name = "credito", length = 30)
    private String credito;

    @Size(max = 30)
    @Column(name = "estado", length = 30)
    private String estado;

    @OneToMany(mappedBy = "cursofk",cascade = CascadeType.REMOVE)
    private List<Nota> notas;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinTable(name = "curso_docentes",
            joinColumns = @JoinColumn(name = "curso_fk", foreignKey = @ForeignKey(name = "fk_cursodoc_cursoid")),
            inverseJoinColumns = @JoinColumn(name = "docente_fk",  foreignKey = @ForeignKey(name = "fk_cursodoc_docenteid")))
    private List<Docente> docentes;

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_fk",  foreignKey = @ForeignKey(name = "fk_curso_alumnoid"))
    private Alumno alumnofk;

}
