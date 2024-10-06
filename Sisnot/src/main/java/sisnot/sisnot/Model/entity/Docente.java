package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "apellido_paterno", length = 100)
    private String apellidoPaterno;

    @Size(max = 100)
    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "dni", unique = true)
    private String dni;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "celular", unique = true)
    private String celular;

    @Size(max = 30)
    @Column(name = "estado", length = 30)
    private String estado;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinTable(name = "docente_alumnos",
            joinColumns = @JoinColumn(name = "docente_fk", foreignKey = @ForeignKey(name = "fk_docalum_docenteid")),
            inverseJoinColumns = @JoinColumn(name = "alumno_fk", foreignKey = @ForeignKey(name = "fk_docalum_alumnoid")))
    private List<Alumno> alumnos;

    @ManyToMany(mappedBy = "docentes")
    private List<Curso> cursos;


}
