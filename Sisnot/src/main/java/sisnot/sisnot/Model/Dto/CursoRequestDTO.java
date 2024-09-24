package sisnot.sisnot.Model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CursoRequestDTO {

    @NotBlank(message = "Nombre del curso es requerido")
    private String nomCurso;

    @NotBlank(message = "Ciclo es requerido")
    private String ciclo;

    @NotBlank(message = "Crédito es requerido")
    private String credito;

    @NotBlank(message = "Estado es requerido")
    private String estado;
}
