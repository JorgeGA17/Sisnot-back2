package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoRequestDTO {

   private String apellidoPaterno;
   private String apellidoMaterno;
   private String nombre;
   private String dni;
   private String direccion;
   private String email;
   private Integer celular;
   private LocalDateTime fechaIngreso;
   private String estado;
}
