package ec.yavirac.yavigestion.modules.administration.services.database.careers;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CareerService {
    Career save(Career career);

    Career update(Long id, Career career);

    void delete(Long id);

    Career findById(Long id);

    Page<Career> findAll(Pageable pageable);

    Long count();

    List<Career> findAllByIds(List<Long> ids);
}
