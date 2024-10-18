package sisnot.sisnot.Model.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaUpdateDTO {
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

}
