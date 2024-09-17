package sisnot.sisnot.Model.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteRequestDTO {

    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String dni;
    private String direccion;
    private String email;
    private Integer celular;
    private String estado;
}
