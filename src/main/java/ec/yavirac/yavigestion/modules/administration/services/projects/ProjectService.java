package ec.yavirac.yavigestion.modules.administration.services.projects;

import java.util.List;
import ec.yavirac.yavigestion.modules.administration.entities.projects.Project;

public interface ProjectService {

    Project save(Project project);

    Project update(Long id, Project project);

    void delete(Long id);

    Project findById(Long id);

    List<Project> findAll();
}
