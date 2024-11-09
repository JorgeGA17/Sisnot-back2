package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Docente;
import sisnot.sisnot.Model.entity.Nota;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DocenteMapper {
    private final ModelMapper modelMapper;

    public DocenteResponseDTO convertToDTO(Docente docente) {
        DocenteResponseDTO docenteResponseDTO = modelMapper.map(docente, DocenteResponseDTO.class);

        List<String> listaCursos = Optional.ofNullable(docente.getCursos())
                .orElse(Collections.emptyList())
                .stream()
                .map(curso -> {
                    String nomCurso = curso.getNomCurso();

                    // Obtener nombres de alumnos y sus notas
                    String alumnosInfo = curso.getAlumnos() != null ?
                            curso.getAlumnos().stream()
                                    .map(alumno -> {
                                        // Obtener las notas del alumno para este curso
                                        List<Nota> notas = curso.getNotas().stream()
                                                .filter(nota -> nota.getAlumnofk().getId().equals(alumno.getId()))
                                                .collect(Collectors.toList());

                                        String notasString = notas.isEmpty() ? "Sin Notas" : formatNotas(notas.get(0));

                                        // Retornar el nombre del alumno y sus notas
                                        return String.format("%s %s %s: [%s]",
                                                alumno.getNombre(),
                                                alumno.getApellidoPaterno(),
                                                alumno.getApellidoMaterno(),
                                                notasString);
                                    })
                                    .collect(Collectors.joining(", "))
                            : "Sin Alumnos";

                    // No incluir saltos de línea en el string
                    return String.format("%s: [Alumnos: [%s]]", nomCurso, alumnosInfo);
                })
                .collect(Collectors.toList());

        docenteResponseDTO.setListaCursos(listaCursos);

        return docenteResponseDTO;
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

    public Docente convertToEntity(DocenteRequestDTO docenteRequestDTO) {
        return modelMapper.map(docenteRequestDTO, Docente.class);
    }

    public List<DocenteResponseDTO> convertToDTO(List<Docente> docentes) {
        return docentes.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
