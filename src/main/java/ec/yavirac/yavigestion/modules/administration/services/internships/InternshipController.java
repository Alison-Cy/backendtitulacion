package ec.yavirac.yavigestion.modules.administration.services.internships;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipRepository internshipRepository;

    // Crear prácticas
    @PostMapping
    public Internship create(@RequestBody Internship internship) {
        internship.setId(null);
        return internshipRepository.save(internship);
    }

    // Actualizar practicas
    @PutMapping("/{id}")
    public Internship update(@PathVariable Long id, @RequestBody Internship internship) {
        internship.setId(id);
        return internshipRepository.save(internship);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public Internship getById(@PathVariable Long id) {
        return internshipRepository.findById(id).orElse(null);
    }

    // Listar todas (ojo por paginado) 
    @GetMapping
    public Page<Internship> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return internshipRepository.findAll(PageRequest.of(page, size));
    }

    // Listado de solo activas
    @GetMapping("/active")
    public Page<Internship> getActive(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return internshipRepository.findByActiveTrue(PageRequest.of(page, size));
    }

    // Buscar por carrera /semestre
    @GetMapping("/search")
    public Page<Internship> search(
            @RequestParam String value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return internshipRepository.findByCareerContainingIgnoreCaseOrSemesterContainingIgnoreCase(
                value, value, PageRequest.of(page, size)
        );
    }

    // delete 
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Internship internship = internshipRepository.findById(id).orElse(null);
        if (internship == null) return "No existe";

        internship.setActive(false);
        internshipRepository.save(internship);

        return "Práctica desactivada";
    }

    // Activar praticas 
    @PutMapping("/activate/{id}")
    public String activate(@PathVariable Long id) {
        Internship internship = internshipRepository.findById(id).orElse(null);
        if (internship == null) return "No existe";

        internship.setActive(true);
        internshipRepository.save(internship);

        return "Práctica activada";
    }
}
