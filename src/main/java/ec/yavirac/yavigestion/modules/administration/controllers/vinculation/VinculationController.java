package ec.yavirac.yavigestion.modules.administration.controllers.vinculation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ec.yavirac.yavigestion.modules.administration.entities.vinculation.Vinculation;
import ec.yavirac.yavigestion.modules.administration.services.vinculation.VinculationService;

@RestController
@RequestMapping("/api/vinculation")
@CrossOrigin("*")
public class VinculationController {

    @Autowired
    private VinculationService service;

    @PostMapping
    public Vinculation create(@RequestBody Vinculation vinculation) {
        return service.save(vinculation);
    }

    @GetMapping("/{id}")
    public Vinculation getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Vinculation> getAll() {
        return service.findAll();
    }

    @PutMapping
    public Vinculation update(@RequestBody Vinculation vinculation) {
        return service.update(vinculation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
