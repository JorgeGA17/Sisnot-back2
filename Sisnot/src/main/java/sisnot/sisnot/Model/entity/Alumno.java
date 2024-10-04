package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
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

    @Column(name = "dni")
    private String dni;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "celular")
    private String celular;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Size(max = 20)
    @Column(name = "estado", length = 20)
    private String estado;

    @OneToMany(mappedBy = "alumnofk",cascade = CascadeType.DETACH)
    private List<Nota> notas;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "docente_alumnos",
            joinColumns = @JoinColumn(name = "alumno_fk ", foreignKey = @ForeignKey(name = "fk_docalum_alumnoid ")),
            inverseJoinColumns = @JoinColumn(name = "docente_fk", foreignKey = @ForeignKey(name = "fk_docalum_docenteid")))
    private List<Docente> docentes;

    @OneToMany(mappedBy = "alumnofk",cascade = CascadeType.DETACH)
    private List<Curso> cursos;



}
