package sisnot.sisnot.Model.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoRequestDTO {

   @NotBlank(message = "Apellido paterno es requerido")
   private String apellidoPaterno;

   @NotBlank(message = "Apellido materno es requerido")
   private String apellidoMaterno;

   @NotBlank(message = "Nombre es requerido")
   private String nombre;

   @NotBlank(message = "DNI es requerido")
   @Pattern(regexp = "[0-9]{8}", message = "DNI debe tener 8 dígitos")
   private String dni;

   @NotBlank(message = "Dirección es requerida")
   private String direccion;

   @NotBlank(message = "Correo electrónico es requerido")
   @Email(message = "Formato de correo electrónico inválido")
   private String email;

   @NotNull(message = "Celular es requerido")
   @Min(value = 900000000, message = "Celular debe tener al menos 9 dígitos")
   @Max(value = 999999999, message = "Celular debe tener como máximo 9 dígitos")
   private String celular;

   @NotBlank(message = "Estado es requerido")
   private String estado;

}
