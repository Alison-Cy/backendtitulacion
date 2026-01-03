package ec.yavirac.yavigestion.modules.administration.repositories;

import ec.yavirac.yavigestion.modules.administration.entities.AcademicPeriods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicPeriodsRepository extends JpaRepository<AcademicPeriods, Long> {
    Page<AcademicPeriods> findAll(Pageable pageable);

    Long countAcademicPeriodsByStatus(String status);
}
