package ec.yavirac.yavigestion.modules.administration.services.enterprises;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprises")
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseRepository repository;

    // crear
    @PostMapping
    public Enterprise create(@RequestBody Enterprise request) {

        // validar ruc unico
        repository.findByRuc(request.getRuc()).ifPresent(e -> {
            throw new RuntimeException("El RUC ya está registrado");
        });

        return repository.save(request);
    }

    // update
    @PutMapping("/{id}")
    public Enterprise update(@PathVariable Long id, @RequestBody Enterprise request) {

        Enterprise existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        // validar por ruc
        repository.findByRuc(request.getRuc()).ifPresent(e -> {
            if (!e.getId().equals(id)) {
                throw new RuntimeException("El RUC ya está registrado por otra empresa");
            }
        });

        existing.setName(request.getName());
        existing.setRuc(request.getRuc());
        existing.setAddress(request.getAddress());
        existing.setPhones(request.getPhones());
        existing.setEmail(request.getEmail());
        existing.setContactPerson(request.getContactPerson());
        existing.setEconomicSector(request.getEconomicSector());

        return repository.save(existing);
    }

    // get id
    @GetMapping("/{id}")
    public Enterprise getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    // lista 
    @GetMapping
    public Page<Enterprise> list(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }

    // lista de solo activas
    @GetMapping("/active")
    public Page<Enterprise> listActive(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(
                repository.findByActiveTrue(),
                pageable,
                repository.count()
        );
    }

    // buscar por nombre o por ruc
    @GetMapping("/search")
    public Page<Enterprise> search(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(
                repository.findByNameContainingIgnoreCaseOrRucContaining(keyword, keyword),
                pageable,
                repository.count()
        );
    }

    // eleimar (ojo softdelete)
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        Enterprise e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        e.setActive(false);
        repository.save(e);
    }

    // activar empresa
    @PutMapping("/activate/{id}")
    public void activate(@PathVariable Long id) {
        Enterprise e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        e.setActive(true);
        repository.save(e);
    }
}
