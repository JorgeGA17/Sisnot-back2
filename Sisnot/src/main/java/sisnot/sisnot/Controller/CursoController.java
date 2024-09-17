package sisnot.sisnot.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sisnot.sisnot.Model.Dto.CursoRequestDTO;
import sisnot.sisnot.Model.Dto.CursoResponseDTO;
import sisnot.sisnot.Service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/Cursos")
@AllArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:4200/")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> getAllCursos() {
        List<CursoResponseDTO> cursos = cursoService.getAllCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> getCursoById(@PathVariable Long id) {
        CursoResponseDTO curso = cursoService.getCursoById(id);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> createCurso(@Validated @RequestBody CursoRequestDTO cursoDTO) {
        CursoResponseDTO createdCurso = cursoService.createCurso(cursoDTO);
        return new ResponseEntity<>(createdCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> updateCurso(@PathVariable Long id, @Valid @RequestBody CursoRequestDTO cursoDTO) {
        CursoResponseDTO updatedCurso = cursoService.updateCurso(id, cursoDTO);
        return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
