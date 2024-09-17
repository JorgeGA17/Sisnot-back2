package sisnot.sisnot.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.CursoMapper;
import sisnot.sisnot.Model.Dto.CursoRequestDTO;
import sisnot.sisnot.Model.Dto.CursoResponseDTO;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Repository.CursoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    private CursoMapper cursoMapper;

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursoMapper.convertToDTO(cursos);
    }

    @Transactional(readOnly = true)
    public CursoResponseDTO getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado" + id));
        return cursoMapper.convertToDTO(curso);
    }

    @Transactional
    public CursoResponseDTO createCurso(CursoRequestDTO cursoRequestDTO) {
        Curso curso = cursoMapper.convertToEntity(cursoRequestDTO);
        cursoRepository.save(curso);
        return cursoMapper.convertToDTO(curso);
    }

    @Transactional
    public CursoResponseDTO updateCurso(Long id, CursoRequestDTO cursoRequestDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado" + id));

        curso.setNomCurso(cursoRequestDTO.getNomCurso());
        curso.setCiclo(cursoRequestDTO.getCiclo());
        curso.setCredito(cursoRequestDTO.getCredito());
        curso.setEstado(cursoRequestDTO.getEstado());
        cursoRepository.save(curso);
        return cursoMapper.convertToDTO(curso);
    }

    @Transactional
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
