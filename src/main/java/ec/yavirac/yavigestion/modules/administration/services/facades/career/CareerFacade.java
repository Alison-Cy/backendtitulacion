package ec.yavirac.yavigestion.modules.administration.services.facades.career;

import ec.yavirac.yavigestion.modules.administration.dtos.request.career.CreateCareerDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.career.UpdateCareerDto;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerDTO;
import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CareerFacade {
    GenericOnlyTextResponse save(CreateCareerDTO career);

    GenericOnlyTextResponse update(Long id, UpdateCareerDto career);

    GenericOnlyTextResponse delete(Long id);

    GenericResponse<CareerDTO> findById(Long id);

    GenericPaginationResponse<CareerDTO> findAll(Pageable pageable);
}
