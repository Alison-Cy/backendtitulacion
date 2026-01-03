package ec.yavirac.yavigestion.modules.administration.services.database.academicPeriods;

import ec.yavirac.yavigestion.modules.administration.entities.AcademicPeriods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AcademicPeriodService {
    AcademicPeriods save(AcademicPeriods project);

    AcademicPeriods update(Long id, AcademicPeriods project);

    void delete(Long id);

    AcademicPeriods findById(Long id);

    Page<AcademicPeriods> findAll(Pageable pageable);

    Long count();

    Long countAll();
}
