package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;

import java.util.List;

@AllArgsConstructor
@Component
public class AlumnoMapper {
    private final ModelMapper modelMapper;

    public Alumno convertToEntity(AlumnoRequestDTO alumnoRequestDTO) {
        return modelMapper.map(alumnoRequestDTO, Alumno.class);
    }

    public AlumnoResponseDTO convertToDTO(Alumno alumno) {
        return modelMapper.map(alumno, AlumnoResponseDTO.class);
    }

    public List<AlumnoResponseDTO> convertToDTO(List<Alumno> alumnos) {
        return alumnos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
