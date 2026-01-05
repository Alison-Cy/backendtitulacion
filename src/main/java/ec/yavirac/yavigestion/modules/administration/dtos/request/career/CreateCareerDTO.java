package ec.yavirac.yavigestion.modules.administration.dtos.request.career;

import ec.yavirac.yavigestion.modules.administration.enums.CareerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCareerDTO {
    private String name;
    private String description;
    private String status;
    private CareerType isDual;

}
