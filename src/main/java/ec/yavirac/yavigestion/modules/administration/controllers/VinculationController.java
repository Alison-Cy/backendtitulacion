package ec.yavirac.yavigestion.modules.administration.controllers;

import java.util.List;

import ec.yavirac.yavigestion.modules.administration.entities.Project;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import ec.yavirac.yavigestion.modules.administration.services.database.vinculation.VinculationService;

@RestController
@RequestMapping("/vinculation")
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
    public GenericPaginationResponse<Vinculation> getAll(@PageableDefault(size = 15) Pageable pageable) {
        Page<Vinculation> page = service.findAll(pageable);
        return ResponseEntity.ok(GenericPaginationResponse
                .<Vinculation>builder()
                .currentPage(pageable.getPageNumber())
                .data(page.getContent())
                .totalPages(page.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .status(200)
                .build()).getBody();
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
