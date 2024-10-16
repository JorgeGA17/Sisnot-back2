package sisnot.sisnot.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sisnot.sisnot.Model.Dto.AlumnoRequestDTO;
import sisnot.sisnot.Model.Dto.AlumnoResponseDTO;
import sisnot.sisnot.Service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/Alumnos")
@AllArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:4200/")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDTO>> getAllAlumnos() {
        List<AlumnoResponseDTO> alumnos = alumnoService.getAllAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> getAlumnoById(@PathVariable Long id) {
        AlumnoResponseDTO alumno = alumnoService.getAlumnoById(id);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> createAlumno(@Validated @RequestBody AlumnoRequestDTO alumnoDTO) {
        AlumnoResponseDTO createdAlumno = alumnoService.createAlumno(alumnoDTO);
        return new ResponseEntity<>(createdAlumno, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> updateAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoRequestDTO alumnoDTO) {
        AlumnoResponseDTO updatedAlumno = alumnoService.updateAlumno(id, alumnoDTO);
        return new ResponseEntity<>(updatedAlumno, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
