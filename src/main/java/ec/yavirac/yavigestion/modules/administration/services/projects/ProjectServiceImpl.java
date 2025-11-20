package ec.yavirac.yavigestion.modules.administration.services.projects;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.yavirac.yavigestion.modules.administration.entities.projects.Project;
import ec.yavirac.yavigestion.modules.administration.repositories.projects.ProjectRepository;

@Service public class ProjectServiceImpl implements ProjectService {
    @Autowired private ProjectRepository repository;
    @Override public Project save(Project project) { return repository.save(project); }
    @Override public Project update(Long id, Project project) {
        Project existing = findById(id);
        existing.setNombreProyecto(project.getNombreProyecto());
        existing.setDireccionEmpresa(project.getDireccionEmpresa());
        existing.setPlazoEjecucion(project.getPlazoEjecucion());
        existing.setFechaInicio(project.getFechaInicio());
        existing.setFechaFinal(project.getFechaFinal());
        existing.setInformeFinal(project.getInformeFinal());
        return repository.save(existing); }
    @Override public void delete(Long id) { repository.deleteById(id); }
    @Override public Project findById(Long id) { return repository.findById(id).orElse(null); }
    @Override public List<Project> findAll() { return repository.findAll(); } }