package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CursoRequestDTO {

    private String nomCurso;
    private String ciclo;
    private String credito;
    private String estado;
}
