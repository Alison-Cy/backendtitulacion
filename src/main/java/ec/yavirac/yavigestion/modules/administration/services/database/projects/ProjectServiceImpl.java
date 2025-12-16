package ec.yavirac.yavigestion.modules.administration.services.database.projects;

import java.util.List;

import ec.yavirac.yavigestion.modules.administration.entities.Project;
import ec.yavirac.yavigestion.modules.core.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ec.yavirac.yavigestion.modules.administration.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se puede actualizar, el registro no existe");
        }
        project.setId(id);
        return repository.save(project);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Project findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}