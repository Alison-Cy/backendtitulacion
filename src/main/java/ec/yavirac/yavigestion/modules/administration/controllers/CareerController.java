package ec.yavirac.yavigestion.modules.administration.controllers;

import ec.yavirac.yavigestion.modules.administration.dtos.request.career.CreateCareerDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.career.UpdateCareerDto;
import ec.yavirac.yavigestion.modules.administration.dtos.response.CareerDTO;
import ec.yavirac.yavigestion.modules.administration.services.facades.career.CareerFacade;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/careers")
public class CareerController {

    @Qualifier("careerFacadeImpl")
    private final CareerFacade careerFacade;

    public CareerController(CareerFacade careerFacade) {
        this.careerFacade = careerFacade;
    }

    @PostMapping
    public ResponseEntity<GenericOnlyTextResponse> create(@RequestBody CreateCareerDTO careerDTO) {
        GenericOnlyTextResponse response = careerFacade.save(careerDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<GenericPaginationResponse<CareerDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        GenericPaginationResponse<CareerDTO> response = careerFacade.findAll(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CareerDTO>> findOneById(@PathVariable Long id) {
        GenericResponse<CareerDTO> period = careerFacade.findById(id);
        return ResponseEntity.status(period.getStatus()).body(period);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> update(@PathVariable Long id, @RequestBody UpdateCareerDto careerDTO) {
        GenericOnlyTextResponse response = careerFacade.update(id, careerDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> delete(@PathVariable Long id) {
        GenericOnlyTextResponse response = careerFacade.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
