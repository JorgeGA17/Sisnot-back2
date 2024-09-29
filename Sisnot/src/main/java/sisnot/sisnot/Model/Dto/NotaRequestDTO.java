package sisnot.sisnot.Model.Dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequestDTO {

    @NotNull(message = "Componente 1 nota es requerida")
    @DecimalMin(value = "0.0", message = "Componente 1 nota debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Componente 1 nota debe ser menor o igual a 20.0")
    private Double componente1Nota;

    @NotNull(message = "Componente 2 nota es requerida")
    @DecimalMin(value = "0.0", message = "Componente 2 nota debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Componente 2 nota debe ser menor o igual a 20.0")
    private Double componente2Nota;

    @NotNull(message = "Nota parcial es requerida")
    @DecimalMin(value = "0.0", message = "Nota parcial debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Nota parcial debe ser menor o igual a 20.0")
    private Double notaParcial;

    @NotNull(message = "Componente 3 nota es requerida")
    @DecimalMin(value = "0.0", message = "Componente 3 nota debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Componente 3 nota debe ser menor o igual a 20.0")
    private Double componente3Nota;

    @NotNull(message = "Componente 4 nota es requerida")
    @DecimalMin(value = "0.0", message = "Componente 4 nota debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Componente 4 nota debe ser menor o igual a 20.0")
    private Double componente4Nota;

    @NotNull(message = "Nota final es requerida")
    @DecimalMin(value = "0.0", message = "Nota final debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "Nota final debe ser menor o igual a 20.0")
    private Double notaFinal;

}
