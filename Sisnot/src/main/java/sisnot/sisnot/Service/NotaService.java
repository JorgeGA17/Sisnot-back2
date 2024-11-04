package sisnot.sisnot.Service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.NotaMapper;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Model.Dto.NotaUpdateDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Curso;
import sisnot.sisnot.Model.entity.Nota;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.CursoRepository;
import sisnot.sisnot.Repository.NotaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;
    private NotaMapper notaMapper;
    private final AlumnoRepository alumnoRepository;
    @Autowired
    private CursoRepository cursoRepository;


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
        // Buscar el alumno y el curso por sus IDs
        Alumno alumno = alumnoRepository.findById(notaRequestDTO.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + notaRequestDTO.getAlumnoId()));
        Curso curso = cursoRepository.findById(notaRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + notaRequestDTO.getCursoId()));

        // Convertir DTO a entidad Nota
        Nota nota = notaMapper.convertToEntity(notaRequestDTO);

        // Calcular la nota parcial
        Double notaParcial = 0.25 * nota.getComponente1Nota() + 0.2 * nota.getComponente2Nota();
        nota.setNotaParcial(notaParcial);

        // Calcular la nota final
        Double notaFinal = 0.25 * nota.getComponente3Nota() + 0.3 * nota.getComponente4Nota();
        nota.setNotaFinal(notaFinal);

        // Calcular la nueva Promedio final
        Double promedioFinal = nota.getNotaParcial() + nota.getNotaFinal();
        nota.setPromedioFinal(promedioFinal);

        // Establecer el alumno y el curso en la nota
        nota.setAlumnofk(alumno);
        nota.setCursofk(curso);

        // Establecer la fecha de registro
        nota.setFechaRegistro(LocalDateTime.now());

        // Guardar la nota en la base de datos
        notaRepository.save(nota);

        return notaMapper.convertToDTO(nota);
    }

    @Transactional
    public NotaResponseDTO updateNota(Long id, NotaRequestDTO notaRequestDTO) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));

        nota.setComponente1Nota(notaRequestDTO.getComponente1Nota());
        nota.setComponente2Nota(notaRequestDTO.getComponente2Nota());
        nota.setComponente3Nota(notaRequestDTO.getComponente3Nota());
        nota.setComponente4Nota(notaRequestDTO.getComponente4Nota());

        Double notaParcial = 0.25 * nota.getComponente1Nota() + 0.2 * nota.getComponente2Nota();
        nota.setNotaParcial(notaParcial);

        Double notaFinal = 0.25 * nota.getComponente3Nota() + 0.3 * nota.getComponente4Nota();
        nota.setNotaFinal(notaFinal);

        Double promedioFinal = nota.getNotaParcial() + nota.getNotaFinal();
        nota.setPromedioFinal(promedioFinal);

        nota.setFechaRegistro(LocalDateTime.now());

        Alumno alumno = alumnoRepository.findById(notaRequestDTO.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + notaRequestDTO.getAlumnoId()));
        Curso curso = cursoRepository.findById(notaRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + notaRequestDTO.getCursoId()));

        nota.setAlumnofk(alumno);
        nota.setCursofk(curso);

        notaRepository.save(nota);

        return notaMapper.convertToDTO(nota);
    }


    @Transactional
    public void updateNotasByAlumnoId(Long alumnoId, Long cursoId, @Valid NotaUpdateDTO notaUpdateDTO) {
        notaRepository.updateNotasByAlumnoId(
                notaUpdateDTO.getComponente1Nota(),
                notaUpdateDTO.getComponente2Nota(),
                notaUpdateDTO.getComponente3Nota(),
                notaUpdateDTO.getComponente4Nota(),
                alumnoId,
                cursoId
        );
    }

    @Transactional
    public void deleteNota(Long id) {
        notaRepository.deleteById(id);
    }

}
