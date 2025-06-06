package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Nota;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AlumnoMapper {
    private final ModelMapper modelMapper;

    public AlumnoResponseDTO convertToDTO(Alumno alumno) {
        AlumnoResponseDTO alumnoResponseDTO = modelMapper.map(alumno, AlumnoResponseDTO.class);

        // Asignar cursoIds al DTO
        List<Long> cursoIds = Optional.ofNullable(alumno.getCursos())
                .orElse(Collections.emptyList())
                .stream()
                .map(Curso::getId)
                .collect(Collectors.toList());// Asegúrate de que Curso tiene un método getId()
        alumnoResponseDTO.setCursoIds(cursoIds);

        List<String> listaCursos = Optional.ofNullable(alumno.getCursos())
                .orElse(Collections.emptyList())
                .stream()
                .map(curso -> {
                    String nomCurso = curso.getNomCurso();
                    String docentesNombres = curso.getDocentes() != null ?
                            curso.getDocentes().stream()
                                    .map(docente -> docente.getNombre() + " " + docente.getApellidoPaterno() + " " + docente.getApellidoMaterno())
                                    .collect(Collectors.joining(", "))
                            : "Sin Docente";

                    List<Nota> notas = curso.getNotas().stream()
                            .filter(nota -> nota.getAlumnofk().getId().equals(alumno.getId()))
                            .collect(Collectors.toList());

                    String notasString = notas.isEmpty() ? "Sin Notas" : formatNotas(notas.get(0));

                    return String.format("%s: [Docentes: [%s], Notas: [%s]]",
                            nomCurso,
                            docentesNombres,
                            notasString);
                })
                .collect(Collectors.toList());

        alumnoResponseDTO.setListaCursos(listaCursos);

        return alumnoResponseDTO;
    }

    // Método auxiliar para formatear las notas
    private String formatNotas(Nota nota) {
        return String.format("c1: %.2f; c2: %.2f; c3: %.2f; c4: %.2f; PromFinal: %.2f",
                nota.getComponente1Nota(),
                nota.getComponente2Nota(),
                nota.getComponente3Nota(),
                nota.getComponente4Nota(),
                nota.getPromedioFinal());
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
