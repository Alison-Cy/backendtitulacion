package ec.yavirac.yavigestion.modules.administration.controllers;

import ec.yavirac.yavigestion.modules.administration.entities.Project;
import ec.yavirac.yavigestion.modules.auth.decorators.HasPermission;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ec.yavirac.yavigestion.modules.administration.services.database.projects.ProjectService;


@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    @HasPermission("project:create")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.ok(service.save(project));
    }

    @GetMapping
    @HasPermission("project:findAll")
    public ResponseEntity<GenericPaginationResponse<Project>> list(@PageableDefault(size = 15) Pageable pageable) {
        Page<Project> page = service.findAll(pageable);
        return ResponseEntity.ok(GenericPaginationResponse
                .<Project>builder()
                .currentPage(pageable.getPageNumber())
                .data(page.getContent())
                .totalPages(page.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .status(200)
                .build());
    }

    @GetMapping("/{id}")
    @HasPermission("project:findById")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @HasPermission("project:update")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(service.update(id, project));
    }

    @DeleteMapping("/{id}")
    @HasPermission("project:delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
