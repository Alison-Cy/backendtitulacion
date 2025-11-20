package ec.yavirac.yavigestion.modules.administration.repositories.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.yavirac.yavigestion.modules.administration.entities.projects.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
