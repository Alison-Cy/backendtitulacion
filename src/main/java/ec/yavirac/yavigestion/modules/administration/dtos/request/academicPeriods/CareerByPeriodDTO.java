package ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CareerByPeriodDTO {
    private List<Long> careerIds;
}
