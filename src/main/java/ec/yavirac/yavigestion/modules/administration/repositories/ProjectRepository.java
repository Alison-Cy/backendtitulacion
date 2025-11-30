package ec.yavirac.yavigestion.modules.administration.repositories;

import ec.yavirac.yavigestion.modules.administration.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
