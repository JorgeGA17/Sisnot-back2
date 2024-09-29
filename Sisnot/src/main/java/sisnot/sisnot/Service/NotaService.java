package sisnot.sisnot.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.NotaMapper;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.entity.Nota;
import sisnot.sisnot.Repository.NotaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;
    private NotaMapper notaMapper;

    @Transactional(readOnly = true)
    public List<NotaResponseDTO> getAllNotas() {
        List<Nota> notas = notaRepository.findAll();
        return notaMapper.convertToDTO(notas);
    }

    @Transactional(readOnly = true)
    public NotaResponseDTO getNotaById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada" + id));
        return notaMapper.convertToDTO(nota);
    }

    @Transactional
    public NotaResponseDTO createNota(NotaRequestDTO notaRequestDTO) {
        Nota nota = notaMapper.convertToEntity(notaRequestDTO);
        nota.setFechaRegistro(LocalDateTime.now());
        notaRepository.save(nota);
        return notaMapper.convertToDTO(nota);
    }

    @Transactional
    public NotaResponseDTO updateNota(Long id, NotaRequestDTO notaRequestDTO) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada" + id));
        // Update nota fields based on notaRequestDTO

        nota.setComponente1Nota(notaRequestDTO.getComponente1Nota());
        nota.setComponente2Nota(notaRequestDTO.getComponente2Nota());
        nota.setNotaParcial(notaRequestDTO.getNotaParcial());
        nota.setComponente3Nota(notaRequestDTO.getComponente3Nota());
        nota.setComponente4Nota(notaRequestDTO.getComponente4Nota());
        nota.setNotaFinal(notaRequestDTO.getNotaFinal());
        nota.setFechaRegistro(LocalDateTime.now());

        notaRepository.save(nota);
        return notaMapper.convertToDTO(nota);
    }

    @Transactional
    public void deleteNota(Long id) {
        notaRepository.deleteById(id);
    }

}
