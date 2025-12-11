package ec.yavirac.yavigestion.modules.administration.services.database.academicPeriods;

import ec.yavirac.yavigestion.modules.administration.repositories.AcademicPeriodsRepository;
import org.springframework.stereotype.Component;

@Component
public class AcademicPeriodServiceImpl implements AcademicPeriodService {
    private final AcademicPeriodsRepository repository;

    public AcademicPeriodServiceImpl(AcademicPeriodsRepository repository) {
        this.repository = repository;
    }


}
