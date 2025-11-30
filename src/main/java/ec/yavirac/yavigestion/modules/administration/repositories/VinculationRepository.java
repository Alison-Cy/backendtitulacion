package ec.yavirac.yavigestion.modules.administration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;

public interface VinculationRepository extends JpaRepository<Vinculation, Long> {

}