package ec.yavirac.yavigestion.modules.auth.repositories;

import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}