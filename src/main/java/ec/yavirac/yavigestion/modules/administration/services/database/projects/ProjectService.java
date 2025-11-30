package ec.yavirac.yavigestion.modules.administration.services.database.projects;

import ec.yavirac.yavigestion.modules.administration.entities.Project;

import java.util.List;

public interface ProjectService {

    Project save(Project project);

    Project update(Long id, Project project);

    void delete(Long id);

    Project findById(Long id);

    List<Project> findAll();
}
