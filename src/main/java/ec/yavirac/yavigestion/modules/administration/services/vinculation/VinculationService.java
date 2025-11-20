package ec.yavirac.yavigestion.modules.administration.services.vinculation;

import java.util.List;
import ec.yavirac.yavigestion.modules.administration.entities.vinculation.Vinculation;

public interface VinculationService {
    Vinculation save(Vinculation vinculation);
    Vinculation findById(Long id);
    List<Vinculation> findAll();
    Vinculation update(Vinculation vinculation);
    void deleteById(Long id);
}
