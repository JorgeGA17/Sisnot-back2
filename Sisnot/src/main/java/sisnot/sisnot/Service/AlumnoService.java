package sisnot.sisnot.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.AlumnoMapper;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.CursoRepository;
import sisnot.sisnot.Repository.NotaRepository;

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
    @Autowired
    private NotaRepository notaRepository;



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

        // Asociar cursos si se proporcionan IDs
        if (alumnoRequestDTO.getCursoIds() != null) {
            List<Curso> cursos = cursoRepository.findAllById(alumnoRequestDTO.getCursoIds());
            alumno.setCursos(cursos); // Asocia los cursos al nuevo alumno
        }

        alumnoRepository.save(alumno); // Guarda el nuevo alumno con los cursos asociados
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
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));

        // Eliminar las notas asociadas
        notaRepository.deleteAll(alumno.getNotas());

        // Luego, eliminar el alumno
        alumnoRepository.delete(alumno);
    }


}
