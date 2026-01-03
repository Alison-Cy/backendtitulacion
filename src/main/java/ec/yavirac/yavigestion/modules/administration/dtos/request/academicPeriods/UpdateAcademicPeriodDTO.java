package ec.yavirac.yavigestion.modules.administration.dtos.request.academicPeriods;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateAcademicPeriodDTO {

    @NotEmpty(message = "El campo name no debe estar vacio")
    @NotNull(message = "El campo name no debe estar vacio")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La startDate es obligatoria")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La endDate es obligatoria")
    private LocalDate endDate;

    private String description;
    @NotEmpty(message = "El campo status no debe estar vacio")
    private String status;
}
