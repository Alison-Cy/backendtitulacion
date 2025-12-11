package ec.yavirac.yavigestion.modules.administration.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import org.springframework.stereotype.Repository;

@Repository
public interface VinculationRepository extends JpaRepository<Vinculation, Long> {
    Page<Vinculation> findAll(Pageable pageable);
}