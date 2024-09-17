package sisnot.sisnot.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sisnot.sisnot.Model.Dto.DocenteRequestDTO;
import sisnot.sisnot.Model.Dto.DocenteResponseDTO;
import sisnot.sisnot.Service.DocenteService;

import java.util.List;

@RestController
@RequestMapping("/Docentes")
@AllArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:4200/")
public class DocenteController {

    private final DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<DocenteResponseDTO>> getAllDocentes() {
        List<DocenteResponseDTO> docentes = docenteService.getAllDocentes();
        return new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteResponseDTO> getDocenteById(@PathVariable Long id) {
        DocenteResponseDTO docente = docenteService.getDocenteById(id);
        return new ResponseEntity<>(docente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocenteResponseDTO> createDocente(@Validated @RequestBody DocenteRequestDTO docenteDTO) {
        DocenteResponseDTO createdDocente = docenteService.createDocente(docenteDTO);
        return new ResponseEntity<>(createdDocente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteResponseDTO> updateDocente(@PathVariable Long id, @Valid @RequestBody DocenteRequestDTO docenteDTO) {
        DocenteResponseDTO updatedDocente = docenteService.updateDocente(id, docenteDTO);
        return new ResponseEntity<>(updatedDocente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
