package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.entity.Nota;

import java.util.List;

@AllArgsConstructor
@Component
public class NotaMapper {

    private final ModelMapper modelMapper;

    public Nota convertToEntity(NotaRequestDTO notaRequestDTO) {
        return modelMapper.map(notaRequestDTO, Nota.class);
    }

    public NotaResponseDTO convertToDTO(Nota nota) {
        return modelMapper.map(nota, NotaResponseDTO.class);
    }

    public List<NotaResponseDTO> convertToDTO(List<Nota> notas) {
        return notas.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
