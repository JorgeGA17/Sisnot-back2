package sisnot.sisnot.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.AlumnoMapper;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Nota;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.CursoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {

    @Autowired
    private AlumnoMapper alumnoMapper;
    private AlumnoRepository alumnoRepository;
    @Autowired
    private CursoRepository cursoRepository;


    @Transactional(readOnly = true)
    public List<AlumnoResponseDTO> getAllAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnoMapper.convertToDTO(alumnos);
    }

    @Transactional(readOnly = true)
    public AlumnoResponseDTO getAlumnoById(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado" +id));
        return alumnoMapper.convertToDTO(alumno);
    }

    @Transactional
    public AlumnoResponseDTO createAlumno(AlumnoRequestDTO alumnoRequestDTO) {
        Alumno alumno = alumnoMapper.convertToEntity(alumnoRequestDTO);
        alumno.setFechaIngreso(LocalDateTime.now());
        alumnoRepository.save(alumno);
        return alumnoMapper.convertToDTO(alumno);
    }

    @Transactional
    public AlumnoResponseDTO updateAlumno(Long id, AlumnoRequestDTO alumnoDTO) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado" + id));

        // Actualizar campos del alumno
        alumno.setApellidoPaterno(alumnoDTO.getApellidoPaterno());
        alumno.setApellidoMaterno(alumnoDTO.getApellidoMaterno());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setDni(alumnoDTO.getDni());
        alumno.setDireccion(alumnoDTO.getDireccion());
        alumno.setEmail(alumnoDTO.getEmail());
        alumno.setCelular(alumnoDTO.getCelular());
        alumno.setEstado(alumnoDTO.getEstado());

        // Eliminar relación anterior
        alumno.getCursos().clear(); // Limpia la lista de cursos

        // Asociar nuevos cursos
        if (alumnoDTO.getCursoIds() != null) {
            List<Curso> cursos = cursoRepository.findAllById(alumnoDTO.getCursoIds());
            alumno.setCursos(cursos); // Asocia los nuevos cursos
        }

        alumnoRepository.save(alumno); // Guarda el alumno con los nuevos cursos
        return alumnoMapper.convertToDTO(alumno);
    }

    @Transactional
    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

    @Transactional
    public void asignarNotas(Long alumnoId, Long cursoId, NotaRequestDTO notaRequestDTO) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado" + alumnoId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado" + cursoId));

        Nota nota = new Nota();
        nota.setComponente1Nota(notaRequestDTO.getComponente1Nota());
        nota.setComponente2Nota(notaRequestDTO.getComponente2Nota());
        nota.setNotaParcial(notaRequestDTO.getNotaParcial());
        nota.setComponente3Nota(notaRequestDTO.getComponente3Nota());
        nota.setComponente4Nota(notaRequestDTO.getComponente4Nota());
        nota.setNotaFinal(notaRequestDTO.getNotaFinal());
        nota.setFechaRegistro(LocalDateTime.now());

        nota.setAlumnofk(alumno);
        nota.setCursofk(curso);

        alumno.getNotas().add(nota); // Agregar nota a la lista de notas del alumno
        curso.getNotas().add(nota);   // Agregar nota a la lista de notas del curso

        // Guardar la nota, el alumno y el curso
        // Esto se puede hacer solo con el alumno, ya que la relación es bidireccional
        alumnoRepository.save(alumno);
    }


}
