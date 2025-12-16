package ec.yavirac.yavigestion.modules.administration.services.database.internships;

import ec.yavirac.yavigestion.modules.administration.entities.Interships;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IntershipService {
    Interships save(Interships Interships);
    Interships findById(Long id);
    Page<Interships> findAll(Pageable pageable);
    Interships update(Interships Interships);
    void deleteById(Long id);
}
