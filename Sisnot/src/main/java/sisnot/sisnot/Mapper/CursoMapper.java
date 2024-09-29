package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.CursoRequestDTO;
import sisnot.sisnot.Model.Dto.CursoResponseDTO;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Docente;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CursoMapper {
    private final ModelMapper modelMapper;

    public Curso convertToEntity(CursoRequestDTO cursoRequestDTO) {
        return modelMapper.map(cursoRequestDTO, Curso.class);
    }

    public CursoResponseDTO convertToDTO(Curso curso) {
        CursoResponseDTO cursoResponseDTO = modelMapper.map(curso, CursoResponseDTO.class);

        if (curso.getDocentes() != null) {
            List<Docente> docentes = curso.getDocentes();
            List<String> listaDocentes = docentes.stream()
                    .map(docente -> docente.getNombre() + " " + docente.getApellidoPaterno() + " " + docente.getApellidoMaterno())
                    .collect(Collectors.toList());
            cursoResponseDTO.setListaDocentes(listaDocentes);
        } else {
            cursoResponseDTO.setListaDocentes(Collections.emptyList());
        }

        return cursoResponseDTO;
    }

    public List<CursoResponseDTO> convertToDTO(List<Curso> cursos) {
        return cursos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
