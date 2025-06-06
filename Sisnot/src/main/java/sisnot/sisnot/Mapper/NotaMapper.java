package sisnot.sisnot.Mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.entity.Nota;

import java.util.List;


@Component
public class NotaMapper {

    private final ModelMapper modelMapper;

    public NotaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // Configuración del ModelMapper
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Ignorar los campos de relaciones en el mapeo
        this.modelMapper.typeMap(NotaRequestDTO.class, Nota.class).addMappings(mapper -> {
            mapper.skip(Nota::setAlumnofk);
            mapper.skip(Nota::setCursofk);
        });
    }

    public Nota convertToEntity(NotaRequestDTO notaRequestDTO) {
        return modelMapper.map(notaRequestDTO, Nota.class);
    }

    public NotaResponseDTO convertToDTO(Nota nota) {
        NotaResponseDTO notaResponseDTO = modelMapper.map(nota, NotaResponseDTO.class);

        // Asignar el ID del alumno
        if (nota.getAlumnofk() != null) {
            notaResponseDTO.setAlumnoId(nota.getAlumnofk().getId());
        }

        // Asignar el ID del curso
        if (nota.getCursofk() != null) {
            notaResponseDTO.setCursoId(nota.getCursofk().getId());
        }

        return notaResponseDTO;
    }

    public List<NotaResponseDTO> convertToDTO(List<Nota> notas) {
        return notas.stream()
                .map(this::convertToDTO)
                .toList();
    }
}