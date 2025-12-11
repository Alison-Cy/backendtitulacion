package ec.yavirac.yavigestion.modules.administration.services.database.internships;

import ec.yavirac.yavigestion.modules.administration.entities.Interships;
import ec.yavirac.yavigestion.modules.administration.entities.Vinculation;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IntershipService {
    Interships save(Interships interships);
    Interships findById(Long id);
    List<Interships> findAll();
    Interships update(Interships interships);
    void deleteById(Long id);
}
