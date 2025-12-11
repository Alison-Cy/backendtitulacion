package ec.yavirac.yavigestion.modules.administration.services.database.vinculation;

import java.util.List;

import ec.yavirac.yavigestion.modules.core.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import ec.yavirac.yavigestion.modules.administration.repositories.VinculationRepository;

@Service
public class VinculationServiceImpl implements VinculationService {
    private final VinculationRepository repository;

    public VinculationServiceImpl(VinculationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vinculation save(Vinculation vinculation) {
        return repository.save(vinculation);
    }

    @Override
    public Vinculation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));
    }

    @Override
    public Page<Vinculation> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Vinculation update(Vinculation vinculation) {
        if (!repository.existsById(vinculation.getId())) {
            throw new NotFoundException("No se puede actualizar, el registro no existe");
        }
        return repository.save(vinculation);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
