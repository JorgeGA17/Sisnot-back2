package sisnot.sisnot.Mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Docente;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DocenteMapper {
    private final ModelMapper modelMapper;

    public DocenteResponseDTO convertToDTO(Docente docente) {
       DocenteResponseDTO docenteResponseDTO = modelMapper.map(docente, DocenteResponseDTO.class);
       List<Alumno> alumnos = docente.getAlumnos();
       List<String> listaAlumnos = alumnos.stream()
               .map(alumno -> alumno.getNombre()+" "+alumno.getApellidoPaterno()+" "+alumno.getApellidoMaterno())
               .collect(Collectors.toList());
       docenteResponseDTO.setListaAlumnos(listaAlumnos);

        List<Curso> cursos = docente.getCursos();
        List<String>listaCursos=cursos.stream()
                .map(Curso::getNomCurso)
                .collect(Collectors.toList());
        docenteResponseDTO.setListaCursos(listaCursos);

       return docenteResponseDTO;

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
