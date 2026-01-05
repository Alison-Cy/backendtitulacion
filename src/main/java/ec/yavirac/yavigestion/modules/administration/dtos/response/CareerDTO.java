package ec.yavirac.yavigestion.modules.administration.dtos.response;

import ec.yavirac.yavigestion.modules.administration.enums.CareerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CareerDTO {
    private Long id;
    private String name;
    private String description;
    private CareerType isDual;
    private String status;
}
