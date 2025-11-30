package ec.yavirac.yavigestion.modules.administration.repositories;

import ec.yavirac.yavigestion.modules.administration.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
