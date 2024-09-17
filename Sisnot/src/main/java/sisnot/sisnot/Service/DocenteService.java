package sisnot.sisnot.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.DocenteMapper;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Model.entity.Docente;
import sisnot.sisnot.Repository.DocenteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;
    private DocenteMapper docenteMapper;

    @Transactional(readOnly = true)
    public List<DocenteResponseDTO> getAllDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return docenteMapper.convertToDTO(docentes);
    }

    @Transactional(readOnly = true)
    public DocenteResponseDTO getDocenteById(Long docentePk) {
        Docente docente = docenteRepository.findById(docentePk)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado" + docentePk));
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public DocenteResponseDTO createDocente(DocenteRequestDTO docenteRequestDTO) {
        Docente docente = docenteMapper.convertToEntity(docenteRequestDTO);
        docenteRepository.save(docente);
        return docenteMapper.convertToDTO(docente);
    }

    @Transactional
    public DocenteResponseDTO updateDocente(Long docentePk, DocenteRequestDTO docenteRequestDTO) {
        Docente docente = docenteRepository.findById(docentePk)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado" + docentePk));

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
    public void deleteDocente(Long docentePk) {
        docenteRepository.deleteById(docentePk);
    }
}
