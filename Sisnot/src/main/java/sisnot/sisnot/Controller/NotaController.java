package sisnot.sisnot.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sisnot.sisnot.Model.Dto.NotaRequestDTO;
import sisnot.sisnot.Model.Dto.NotaResponseDTO;
import sisnot.sisnot.Service.NotaService;

import java.util.List;

@RestController
@RequestMapping("/Notas")
@AllArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:4200/")
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> getAllNotas() {
        List<NotaResponseDTO> notas = notaService.getAllNotas();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> getNotaById(@PathVariable Long id) {
        NotaResponseDTO nota = notaService.getNotaById(id);
        return new ResponseEntity<>(nota, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> createNota(@Validated @RequestBody NotaRequestDTO notaDTO) {
        NotaResponseDTO createdNota = notaService.createNota(notaDTO);
        return new ResponseEntity<>(createdNota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> updateNota(@PathVariable Long id, @Valid @RequestBody NotaRequestDTO notaDTO) {
        NotaResponseDTO updatedNota = notaService.updateNota(id, notaDTO);
        return new ResponseEntity<>(updatedNota, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteNota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
