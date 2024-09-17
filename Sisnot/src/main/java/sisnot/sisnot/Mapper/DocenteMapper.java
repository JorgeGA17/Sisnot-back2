package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Model.entity.Docente;

import java.util.List;

@AllArgsConstructor
@Component
public class DocenteMapper {
    private final ModelMapper modelMapper;

    public Docente convertToEntity(DocenteRequestDTO docenteRequestDTO) {
        return modelMapper.map(docenteRequestDTO, Docente.class);
    }

    public DocenteResponseDTO convertToDTO(Docente docente) {
        return modelMapper.map(docente, DocenteResponseDTO.class);
    }

    public List<DocenteResponseDTO> convertToDTO(List<Docente> docentes) {
        return docentes.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
