package ec.yavirac.yavigestion.modules.administration.services.facades.career;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CareerFacade {
    GenericOnlyTextResponse save(Career career);

    GenericOnlyTextResponse update(Long id, Career career);

    GenericOnlyTextResponse delete(Long id);

    GenericResponse<Career> findById(Long id);

    GenericPaginationResponse<Career> findAll(Pageable pageable);
}
