package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "alumnos")

public class Alumno {
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

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Size(max = 20)
    @Column(name = "estado", length = 20)
    private String estado;

    @OneToMany(mappedBy = "alumnofk",cascade = CascadeType.DETACH)
    private List<Nota> notas= new ArrayList<>();

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "inscripción",
            joinColumns = @JoinColumn(name = "alumno_fk", foreignKey = @ForeignKey(name = "fk_inscripcion_alumnoid")),
            inverseJoinColumns = @JoinColumn(name = "curso_fk", foreignKey = @ForeignKey(name = "fk_inscripcion_cursoid")))
    private List<Curso> cursos;

}
