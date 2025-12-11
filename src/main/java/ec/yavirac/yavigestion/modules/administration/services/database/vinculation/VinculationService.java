package ec.yavirac.yavigestion.modules.administration.services.database.vinculation;

import java.util.List;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VinculationService {
    Vinculation save(Vinculation vinculation);
    Vinculation findById(Long id);
    Page<Vinculation> findAll(Pageable pageable);
    Vinculation update(Vinculation vinculation);
    void deleteById(Long id);
}
