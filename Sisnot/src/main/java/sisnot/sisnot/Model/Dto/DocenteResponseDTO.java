package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteResponseDTO {
    private Long id;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String dni;
    private String direccion;
    private String email;
    private String celular;
    private String estado;


    private List<String> listaAlumnos;
    private List<String> listaCursos;


}
