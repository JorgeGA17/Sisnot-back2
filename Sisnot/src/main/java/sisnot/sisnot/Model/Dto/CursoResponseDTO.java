package sisnot.sisnot.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDTO {

    private Long id;
    private String nomCurso;
    private String ciclo;
    private String credito;
    private String estado;
    private List<String> listaDocentes;

}
