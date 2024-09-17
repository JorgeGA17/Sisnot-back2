package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Docente;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AlumnoMapper {
    private final ModelMapper modelMapper;


    public AlumnoResponseDTO convertToDTO(Alumno alumno) {
        AlumnoResponseDTO alumnoResponseDTO = modelMapper.map(alumno, AlumnoResponseDTO.class);
        List<Docente> docentes = alumno.getDocentes();
        List<String> docenteNombres = docentes.stream()
                .map(docente -> docente.getNombre() + " " + docente.getApellidoPaterno() + " " + docente.getApellidoMaterno())
                .collect(Collectors.toList());
        alumnoResponseDTO.setDocenteNombres(docenteNombres);
        return alumnoResponseDTO;
    }

    public Alumno convertToEntity(AlumnoRequestDTO alumnoRequestDTO) {
        return modelMapper.map(alumnoRequestDTO, Alumno.class);
    }


    public List<AlumnoResponseDTO> convertToDTO(List<Alumno> alumnos) {
        return alumnos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
