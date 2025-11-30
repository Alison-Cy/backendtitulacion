package ec.yavirac.yavigestion.modules.administration.services.database.vinculation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import ec.yavirac.yavigestion.modules.administration.repositories.VinculationRepository;

@Service
public class VinculationServiceImpl implements VinculationService {

    @Autowired
    private VinculationRepository repository;

    @Override
    public Vinculation save(Vinculation vinculation) {
        return repository.save(vinculation);
    }

    @Override
    public Vinculation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    @Override
    public List<Vinculation> findAll() {
        return repository.findAll();
    }

    @Override
    public Vinculation update(Vinculation vinculation) {
        if (!repository.existsById(vinculation.getId())) {
            throw new RuntimeException("No se puede actualizar, el registro no existe");
        }
        return repository.save(vinculation);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
