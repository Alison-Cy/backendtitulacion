package ec.yavirac.yavigestion.modules.administration.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashboardStatsDTO {
    private Long totalUsers;
    private Long totalCareers;
    private Long totalAcademicPeriods;
    private Long totalActivePeriods;
}
