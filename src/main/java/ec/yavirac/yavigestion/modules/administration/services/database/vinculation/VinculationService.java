package ec.yavirac.yavigestion.modules.administration.services.database.vinculation;

import java.util.List;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;

public interface VinculationService {
    Vinculation save(Vinculation vinculation);
    Vinculation findById(Long id);
    List<Vinculation> findAll();
    Vinculation update(Vinculation vinculation);
    void deleteById(Long id);
}
