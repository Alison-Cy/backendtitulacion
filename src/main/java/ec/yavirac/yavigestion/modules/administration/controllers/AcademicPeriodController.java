package ec.yavirac.yavigestion.modules.administration.controllers;

import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CareerByPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.AcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.CreateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods.UpdateAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerAcademicPeriodDTO;
import ec.yavirac.yavigestion.modules.administration.services.facades.academicPeriod.AcademicPeriodFacade;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/periods")
public class AcademicPeriodController {

    @Qualifier("academicPeriodFacadeImpl")
    private final AcademicPeriodFacade academicPeriodFacade;

    public AcademicPeriodController(AcademicPeriodFacade academicPeriodFacade) {
        this.academicPeriodFacade = academicPeriodFacade;
    }

    @PostMapping
    public ResponseEntity<GenericOnlyTextResponse> create(@RequestBody CreateAcademicPeriodDTO academicPeriodDTO) {
        GenericOnlyTextResponse response = academicPeriodFacade.save(academicPeriodDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<GenericPaginationResponse<AcademicPeriodDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        GenericPaginationResponse<AcademicPeriodDTO> response = academicPeriodFacade.findAll(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AcademicPeriodDTO>> findOneById(@PathVariable Long id) {
        GenericResponse<AcademicPeriodDTO> period = academicPeriodFacade.findById(id);
        return ResponseEntity.status(period.getStatus()).body(period);
    }


    @GetMapping("/{id}/career")
    public ResponseEntity<GenericResponse<CareerAcademicPeriodDTO>> findCareersPeriod(@PathVariable Long id) {
        GenericResponse<CareerAcademicPeriodDTO> period = academicPeriodFacade.findCareersByPeriod(id);
        return ResponseEntity.status(period.getStatus()).body(period);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> update(@PathVariable Long id, @RequestBody UpdateAcademicPeriodDTO academicPeriodDTO) {
        GenericOnlyTextResponse response = academicPeriodFacade.update(id, academicPeriodDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}/assignCareer")
    public ResponseEntity<GenericOnlyTextResponse> assignCareers(@PathVariable Long id, @RequestBody CareerByPeriodDTO academicPeriodDTO) {
        GenericOnlyTextResponse response = academicPeriodFacade.assignCareers(id, academicPeriodDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> delete(@PathVariable Long id) {
        GenericOnlyTextResponse response = academicPeriodFacade.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
