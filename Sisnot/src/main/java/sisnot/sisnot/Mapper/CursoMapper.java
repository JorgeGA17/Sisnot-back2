package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.CursoRequestDTO;
import sisnot.sisnot.Model.Dto.CursoResponseDTO;
import sisnot.sisnot.Model.entity.Curso;

import java.util.List;

@AllArgsConstructor
@Component
public class CursoMapper {
    private final ModelMapper modelMapper;

    public Curso convertToEntity(CursoRequestDTO cursoRequestDTO) {
        return modelMapper.map(cursoRequestDTO, Curso.class);
    }

    public CursoResponseDTO convertToDTO(Curso curso) {
        return modelMapper.map(curso, CursoResponseDTO.class);
    }

    public List<CursoResponseDTO> convertToDTO(List<Curso> cursos) {
        return cursos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
