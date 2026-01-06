package ec.yavirac.yavigestion.modules.administration.dtos.response;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CareerAcademicPeriodDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long totalCareers;
    private Long totalActiveCareers;
    private List<CareerDTO> careers;
    private Long totalTraditional;
    private Long totalDual;
}
