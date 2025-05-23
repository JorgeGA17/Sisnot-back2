package sisnot.sisnot.Model.Dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequestDTO {
    @NotNull(message = "Componente 1 nota es requerida")
    @Positive
    private Double componente1Nota;

    @NotNull(message = "Componente 2 nota es requerida")
    @Positive
    private Double componente2Nota;

    @NotNull(message = "Componente 3 nota es requerida")
    @Positive
    private Double componente3Nota;

    @NotNull(message = "Componente 4 nota es requerida")
    @Positive
    private Double componente4Nota;

    @NotNull(message = "ID del alumno es requerido")
    private Long alumnoId;

    @NotNull(message = "ID del curso es requerido")
    private Long cursoId;
}
