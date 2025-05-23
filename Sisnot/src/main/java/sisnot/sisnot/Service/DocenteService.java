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

import java.util.ArrayList;
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
    public DocenteResponseDTO createDocente(DocenteRequestDTO docenteDTO) {
        Docente docente = docenteMapper.convertToEntity(docenteDTO);

        // Asociar cursos
        if (docenteDTO.getCursoIds() != null) {
            List<Curso> cursos = cursoRepository.findAllById(docenteDTO.getCursoIds());
            docente.setCursos(cursos); // Asocia los cursos al docente
        }

        docenteRepository.save(docente);
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public DocenteResponseDTO updateDocente(Long id, DocenteRequestDTO docenteDTO) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado" + id));

        // Actualizar campos del docente
        docente.setApellidoPaterno(docenteDTO.getApellidoPaterno());
        docente.setApellidoMaterno(docenteDTO.getApellidoMaterno());
        docente.setNombre(docenteDTO.getNombre());
        docente.setDni(docenteDTO.getDni());
        docente.setDireccion(docenteDTO.getDireccion());
        docente.setEmail(docenteDTO.getEmail());
        docente.setCelular(docenteDTO.getCelular());
        docente.setEstado(docenteDTO.getEstado());

        // Eliminar relación anterior
        docente.getCursos().clear(); // Limpia la lista de cursos
        docenteRepository.save(docente); // Guarda el docente sin cursos

        // Asociar nuevos cursos
        if (docenteDTO.getCursoIds() != null) {
            List<Curso> cursos = cursoRepository.findAllById(docenteDTO.getCursoIds());
            docente.setCursos(cursos); // Asocia los nuevos cursos
        }

        docenteRepository.save(docente); // Guarda el docente con los nuevos cursos
        return docenteMapper.convertToDTO(docente);
    }


    @Transactional
    public void deleteDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado"));

        docenteRepository.delete(docente);
    }
}
