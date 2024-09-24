package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Docente;
import sisnot.sisnot.Model.entity.Nota;

import java.util.Collections;
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

        List<Nota> notas = alumno.getNotas();
        List<String> notasFinales = notas.stream()
                .map(nota -> " C1: " + nota.getComponente1Nota() +
                        " C2: " + nota.getComponente2Nota() +
                        " Cp: " + nota.getNotaParcial() +
                        " C3: " + nota.getComponente3Nota() +
                        " C4: " + nota.getComponente4Nota() +
                        " CF: " + nota.getNotaFinal())
                .collect(Collectors.toList());
        alumnoResponseDTO.setNotasFinales(notasFinales);

        List<Curso> cursos = alumno.getCursos();
        List<String>listaCursos=cursos.stream()
                .map(Curso::getNomCurso)
                .collect(Collectors.toList());
        alumnoResponseDTO.setListaCursos(listaCursos);

        return alumnoResponseDTO;
    }

    public NotaResponseDTO convertNotaToDTO(Nota nota) {
        return modelMapper.map(nota, NotaResponseDTO.class);
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
