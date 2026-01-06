package ec.yavirac.yavigestion.modules.administration.services.facades.academicPeriod;

import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CareerByPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.AcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CreateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.UpdateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerDTO;
import ec.yavirac.yavigestion.modules.administration.entities.AcademicPeriods;
import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.enums.CareerType;
import ec.yavirac.yavigestion.modules.administration.services.database.academicPeriods.AcademicPeriodService;
import ec.yavirac.yavigestion.modules.administration.services.database.careers.CareerService;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
public class AcademicPeriodFacadeImpl implements AcademicPeriodFacade {

    @Qualifier("academicPeriodServiceImpl")
    private final AcademicPeriodService academicPeriodService;

    @Qualifier("careerServiceImpl")
    private final CareerService careerService;

    public AcademicPeriodFacadeImpl(AcademicPeriodService academicPeriodService, CareerService careerService) {
        this.academicPeriodService = academicPeriodService;
        this.careerService = careerService;
    }

    @Override
    public GenericOnlyTextResponse save(CreateAcademicPeriodDTO period) {
        log.info("AcademicPeriodFacadeImpl::save");
        if(period.getStartDate().isAfter(period.getEndDate() )) {
            return GenericOnlyTextResponse.builder().message("Deben ingresar fechas validas").status(400).build();
        }

        log.info("Creando el periodo");
        AcademicPeriods academicPeriod = new AcademicPeriods();
        academicPeriod.setName(period.getName());
        academicPeriod.setDescription(period.getDescription());
        academicPeriod.setStartDate(period.getStartDate());
        academicPeriod.setEndDate(period.getEndDate());
        academicPeriod.setStatus(period.getStatus());
        AcademicPeriods periods = academicPeriodService.save(academicPeriod);

        if (periods == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar el periodo").status(500).build();
        }

        log.info("Se guardar el periodo");
        return GenericOnlyTextResponse.builder()
                .status(201)
                .message("Se ha creado exitosamente").build();
    }

    @Override
    public GenericOnlyTextResponse update(Long id, UpdateAcademicPeriodDTO period) {

        if(period.getStartDate().isAfter(period.getEndDate() )) {
            return GenericOnlyTextResponse.builder().status(400).message("Deben ingresar fechas validas al actualizar").build();
        }

        AcademicPeriods academicPeriod = new AcademicPeriods();
        academicPeriod.setName(period.getName());
        academicPeriod.setDescription(period.getDescription());
        academicPeriod.setStartDate(period.getStartDate());
        academicPeriod.setEndDate(period.getEndDate());
        academicPeriod.setStatus(period.getStatus());
        AcademicPeriods periods = academicPeriodService.update(id, academicPeriod);

        if (periods == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar el periodo").status(500).build();
        }
        return GenericOnlyTextResponse.builder()
                .status(200)
                .message("Se ha actualizado exitosamente").build();
    }

    @Override
    public GenericOnlyTextResponse delete(Long id) {
        academicPeriodService.delete(id);
        return GenericOnlyTextResponse.builder().status(203).message("Se ha eliminado el periodo").build();
    }

    @Override
    public GenericResponse<AcademicPeriodDTO> findById(Long id) {
        AcademicPeriods academicPeriod = academicPeriodService.findById(id);
        AcademicPeriodDTO transformedPeriod = AcademicPeriodDTO.builder()
                .id(academicPeriod.getId())
                .name(academicPeriod.getName())
                .description(academicPeriod.getDescription())
                .startDate(academicPeriod.getStartDate())
                .status(academicPeriod.getStatus())
                .endDate(academicPeriod.getEndDate()).build();
        return GenericResponse.<AcademicPeriodDTO>builder().message("Se ha encontrado el registro").data(transformedPeriod).status(200).build();
    }

    @Override
    public GenericPaginationResponse<AcademicPeriodDTO> findAll(Pageable pageable) {
        Page<AcademicPeriods> page = academicPeriodService.findAll(pageable);
        List<AcademicPeriodDTO> transformedPeriods = page.getContent().stream()
                .map(period -> AcademicPeriodDTO
                    .builder()
                        .description(period.getDescription())
                        .name(period.getName())
                        .totalCareers((long) period.getCareers().size())
                        .startDate(period.getStartDate())
                        .endDate(period.getEndDate())
                        .id(period.getId())
                        .status(period.getStatus())
                        .build()).toList();

        return ResponseEntity.ok(GenericPaginationResponse
                .<AcademicPeriodDTO>builder()
                .currentPage(pageable.getPageNumber())
                .data(transformedPeriods)
                .totalPages(page.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .status(200)
                .build()).getBody();
    }

    @Override
    public GenericResponse<CareerAcademicPeriodDTO> findCareersByPeriod(Long id) {
        AcademicPeriods academicPeriods = academicPeriodService.findById(id);
        Set<Career> careers = academicPeriods.getCareers();
        Long totalDual = careers.stream().filter(career -> career.getType().equals(CareerType.DUAL)).count();
        Long totalTraditional = careers.stream()
                .filter(career -> career.getType().equals(CareerType.TRADITIONAL))
                .count();
        Long totalActiveCareers = careers.stream()
                .filter(career -> career.getStatus().equals(StatusConst.ACTIVE))
                .count();

        CareerAcademicPeriodDTO careerAcademicPeriodDTO = CareerAcademicPeriodDTO
                .builder()
                .totalDual(totalDual)
                .totalTraditional(totalTraditional)
                .id(academicPeriods.getId())
                .name(academicPeriods.getName())
                .totalCareers((long) careers.size())
                .startDate(academicPeriods.getStartDate())
                .endDate(academicPeriods.getEndDate())
                .description(academicPeriods.getDescription())
                .status(academicPeriods.getStatus())
                .totalActiveCareers(totalActiveCareers)
                .careers(academicPeriods.getCareers()
                        .stream()
                        .map(career -> CareerDTO
                                .builder()
                                .id(career.getId())
                                .name(career.getName())
                                .description(career.getDescription())
                                .isDual(career.getType())
                                .build())
                        .toList())
                .build();

        return GenericResponse
                .<CareerAcademicPeriodDTO>builder()
                .status(200)
                .data(careerAcademicPeriodDTO)
                .build();
    }

    @Override
    public GenericOnlyTextResponse assignCareers(Long id, CareerByPeriodDTO careerAcademicPeriodDTO) {
        AcademicPeriods academicPeriods = academicPeriodService.findById(id);
        Set<Career> careers = new HashSet<>(careerService.findAllByIds(careerAcademicPeriodDTO.getCareerIds()));
        academicPeriods.setCareers(careers);
        academicPeriodService.save(academicPeriods);
        return GenericOnlyTextResponse
                .builder()
                .status(200).message("Se ha asignado las carreras respectivas")
                .build();
    }

}
