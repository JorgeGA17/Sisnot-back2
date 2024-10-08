package sisnot.sisnot.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.DocenteMapper;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Docente;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.CursoRepository;
import sisnot.sisnot.Repository.DocenteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;
    private DocenteMapper docenteMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @Transactional(readOnly = true)
    public List<DocenteResponseDTO> getAllDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return docenteMapper.convertToDTO(docentes);
    }

    @Transactional(readOnly = true)
    public DocenteResponseDTO getDocenteById(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado" + id));
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public DocenteResponseDTO createDocente(DocenteRequestDTO docenteRequestDTO) {
        Docente docente = docenteMapper.convertToEntity(docenteRequestDTO);
        docenteRepository.save(docente);
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public DocenteResponseDTO updateDocente(Long id, DocenteRequestDTO docenteRequestDTO) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado" + id));

        docente.setApellidoPaterno(docenteRequestDTO.getApellidoPaterno());
        docente.setApellidoMaterno(docenteRequestDTO.getApellidoMaterno());
        docente.setNombre(docenteRequestDTO.getNombre());
        docente.setDni(docenteRequestDTO.getDni());
        docente.setDireccion(docenteRequestDTO.getDireccion());
        docente.setEmail(docenteRequestDTO.getEmail());
        docente.setCelular(docenteRequestDTO.getCelular());
        docente.setEstado(docenteRequestDTO.getEstado());


        docenteRepository.save(docente);
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public void deleteDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado"));

        for (Alumno alumno : docente.getAlumnos()) {
            alumno.getDocentes().remove(docente);
            alumnoRepository.save(alumno); // Guarda el alumno después de eliminar la relación
        }

        // Eliminar relaciones con cursos
        for (Curso curso : docente.getCursos()) {
            curso.getDocentes().remove(docente);
            cursoRepository.save(curso); // Guarda el curso después de eliminar la relación
        }


        docenteRepository.delete(docente);

    }
}
