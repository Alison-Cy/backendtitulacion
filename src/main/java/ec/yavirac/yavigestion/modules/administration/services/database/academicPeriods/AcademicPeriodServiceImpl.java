package ec.yavirac.yavigestion.modules.administration.services.database.academicPeriods;

import ec.yavirac.yavigestion.modules.administration.entities.AcademicPeriods;
import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.repositories.AcademicPeriodsRepository;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import ec.yavirac.yavigestion.modules.core.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AcademicPeriodServiceImpl implements AcademicPeriodService {
    private final AcademicPeriodsRepository repository;

    public AcademicPeriodServiceImpl(AcademicPeriodsRepository repository) {
        this.repository = repository;
    }


    @Override
    public AcademicPeriods save(AcademicPeriods project) {
        return repository.save(project);
    }

    @Override
    public AcademicPeriods update(Long id, AcademicPeriods project) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se puede actualizar, el registro no existe");
        }
        project.setId(id);
        return repository.save(project);    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AcademicPeriods findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
    }

    @Override
    public Page<AcademicPeriods> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long count() {
        return repository.countAcademicPeriodsByStatus(StatusConst.ACTIVE);
    }

    @Override
    public Long countAll() {
        return repository.count();
    }
}
