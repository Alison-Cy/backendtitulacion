package ec.yavirac.yavigestion.modules.administration.services.database.careers;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.repositories.CareerRepository;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import ec.yavirac.yavigestion.modules.core.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CareerServiceImpl implements CareerService {

    private final CareerRepository repository;

    public CareerServiceImpl(CareerRepository repository) {
        this.repository = repository;
    }


    @Override
    public Career save(Career career) {
        return repository.save(career);
    }

    @Override
    public Career update(Long id, Career career) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se puede actualizar, el registro no existe");
        }
        career.setId(id);
        return repository.save(career);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Career findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
    }

    @Override
    public Page<Career> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long count() {
        return repository.countCareerByStatus(StatusConst.ACTIVE);
    }

    @Override
    public List<Career> findAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
