package ec.yavirac.yavigestion.modules.administration.services.facades.career;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.repositories.CareerRepository;
import ec.yavirac.yavigestion.modules.administration.services.database.careers.CareerService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CareerFacadeImpl implements CareerFacade {

    @Qualifier("careerServiceImpl")
    private CareerService careerRepository;

    @Override
    public GenericOnlyTextResponse save(Career career) {

        return null;
    }

    @Override
    public GenericOnlyTextResponse update(Long id, Career career) {
        return null;
    }

    @Override
    public GenericOnlyTextResponse delete(Long id) {
        return null;
    }

    @Override
    public GenericResponse<Career> findById(Long id) {
        return null;
    }

    @Override
    public GenericPaginationResponse<Career> findAll(Pageable pageable) {
        return null;
    }
}
