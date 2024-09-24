package sisnot.sisnot.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisnot.sisnot.Mapper.AlumnoMapper;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.DocenteRepository;
import sisnot.sisnot.Repository.NotaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {

    @Autowired
    private AlumnoMapper alumnoMapper;
    private AlumnoRepository alumnoRepository;


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
        alumnoRepository.save(alumno);
        return alumnoMapper.convertToDTO(alumno);
    }

    @Transactional
    public AlumnoResponseDTO updateAlumno(Long id, AlumnoRequestDTO alumnoRequestDTO) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado" + id));

        alumno.setApellidoPaterno(alumnoRequestDTO.getApellidoPaterno());
        alumno.setApellidoMaterno(alumnoRequestDTO.getApellidoMaterno());
        alumno.setNombre(alumnoRequestDTO.getNombre());
        alumno.setDni(alumnoRequestDTO.getDni());
        alumno.setDireccion(alumnoRequestDTO.getDireccion());
        alumno.setEmail(alumnoRequestDTO.getEmail());
        alumno.setCelular(alumnoRequestDTO.getCelular());
        alumno.setEstado(alumnoRequestDTO.getEstado());
        alumno.setFechaIngreso(LocalDateTime.now());

        alumnoRepository.save(alumno);
        return alumnoMapper.convertToDTO(alumno);
    }

    @Transactional
    public void deleteAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }
}
