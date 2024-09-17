package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaResponseDTO {
    private Long id;
    private Double componente1Nota;
    private Double componente2Nota;
    private Double notaParcial;
    private Double componente3Nota;
    private Double componente4Nota;
    private Double notaFinal;
    private LocalDateTime fechaRegistro;
}
