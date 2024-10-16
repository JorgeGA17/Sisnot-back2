package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoResponseDTO {

    private Long id;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String dni;
    private String direccion;
    private String email;
    private String celular;
    private LocalDateTime fechaIngreso;
    private String estado;

    private List<String> listaCursos;

}

