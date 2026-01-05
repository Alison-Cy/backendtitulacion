package ec.yavirac.yavigestion.modules.administration.services.facades.career;


import ec.yavirac.yavigestion.modules.administration.dtos.request.career.CreateCareerDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.career.UpdateCareerDto;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerDTO;
import ec.yavirac.yavigestion.modules.administration.entities.Career;
import ec.yavirac.yavigestion.modules.administration.services.database.careers.CareerService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CareerFacadeImpl implements CareerFacade {

    @Qualifier("careerServiceImpl")
    private final CareerService careerService;

    public CareerFacadeImpl(CareerService careerService) {
        this.careerService = careerService;
    }


    @Override
    public GenericOnlyTextResponse save(CreateCareerDTO career) {
        log.info("CareerFacadeImpl::save");
  

        log.info("Creando la carrera");
        Career entity = new Career();
        entity.setName(career.getName());
        entity.setDescription(career.getDescription());
        entity.setType(career.getIsDual());
        entity.setStatus(career.getStatus());
        Career careers = careerService.save(entity);

        if (careers == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar la carrera").status(500).build();
        }

        log.info("Se guardar la carrera");
        return GenericOnlyTextResponse.builder()
                .status(201)
                .message("Se ha creado exitosamente").build();
    }

    @Override
    public GenericOnlyTextResponse update(Long id, UpdateCareerDto career) {

        Career entity = new Career();
        entity.setName(career.getName());
        entity.setDescription(career.getDescription());
        entity.setType(career.getIsDual());
        entity.setStatus(career.getStatus());
        Career careers = careerService.update(id, entity);

        if (careers == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar la carrera").status(500).build();
        }
        return GenericOnlyTextResponse.builder()
                .status(200)
                .message("Se ha actualizado exitosamente").build();
    }

    @Override
    public GenericOnlyTextResponse delete(Long id) {
        careerService.delete(id);
        return GenericOnlyTextResponse.builder().status(203).message("Se ha eliminado la carrera").build();
    }

    @Override
    public GenericResponse<CareerDTO> findById(Long id) {
        Career entity = careerService.findById(id);
        CareerDTO transformedPeriod = CareerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isDual(entity.getType())
                .status(entity.getStatus())
                .build();
        return GenericResponse.<CareerDTO>builder().message("Se ha encontrado el registro").data(transformedPeriod).status(200).build();
    }

    @Override
    public GenericPaginationResponse<CareerDTO> findAll(Pageable pageable) {
        Page<Career> page = careerService.findAll(pageable);
        List<CareerDTO> transformedPeriods = page.getContent().stream()
                .map(career -> CareerDTO
                        .builder()
                        .id(career.getId())
                        .name(career.getName())
                        .description(career.getDescription())
                        .isDual(career.getType())
                        .status(career.getStatus())
                        .build()).toList();

        return ResponseEntity.ok(GenericPaginationResponse
                .<CareerDTO>builder()
                .currentPage(pageable.getPageNumber())
                .data(transformedPeriods)
                .totalPages(page.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .status(200)
                .build()).getBody();
    }
}
