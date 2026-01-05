package ec.yavirac.yavigestion.modules.administration.services.facades.academicPeriod;

import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CareerByPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.AcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CreateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.UpdateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AcademicPeriodFacade {
    GenericOnlyTextResponse save(CreateAcademicPeriodDTO period);
    GenericOnlyTextResponse update(Long id, UpdateAcademicPeriodDTO period);
    GenericOnlyTextResponse delete(Long id);
    GenericResponse<AcademicPeriodDTO> findById(Long id);
    GenericPaginationResponse<AcademicPeriodDTO> findAll(Pageable pageable);
    GenericOnlyTextResponse assignCareers(Long id, CareerByPeriodDTO careerAcademicPeriodDTO);
    GenericResponse<CareerAcademicPeriodDTO> findCareersByPeriod(Long id);
    }
