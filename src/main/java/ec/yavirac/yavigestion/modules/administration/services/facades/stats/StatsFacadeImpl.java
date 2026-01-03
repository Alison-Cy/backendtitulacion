package ec.yavirac.yavigestion.modules.administration.services.facades.stats;

import ec.yavirac.yavigestion.modules.administration.dtos.response.DashboardStatsDTO;
import ec.yavirac.yavigestion.modules.administration.services.database.academicPeriods.AcademicPeriodService;
import ec.yavirac.yavigestion.modules.administration.services.database.careers.CareerService;
import ec.yavirac.yavigestion.modules.auth.services.user.UserService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatsFacadeImpl implements StatsFacade {

    @Qualifier("userServiceImpl")
    private final UserService userService;

    @Qualifier("careerServiceImpl")
    private final CareerService careerService;

    @Qualifier("academicPeriodServiceImpl")
    private final AcademicPeriodService academicPeriodService;

    public StatsFacadeImpl(UserService userService, CareerService careerService, AcademicPeriodService academicPeriodService) {
        this.userService = userService;
        this.careerService = careerService;
        this.academicPeriodService = academicPeriodService;
    }


    @Override
    public GenericResponse<DashboardStatsDTO> getDashboardStats() {
        Long totalUsers = userService.count();
        Long totalCareers = careerService.count();
        Long totalAcademicPeriods = academicPeriodService.countAll();
        Long totalActivePeriods = academicPeriodService.count();

        return GenericResponse.<DashboardStatsDTO>builder()
                .message("Se ha obtenido las stats")
                .status(200)
                .data(DashboardStatsDTO
                        .builder()
                        .totalUsers(totalUsers)
                        .totalCareers(totalCareers)
                        .totalAcademicPeriods(totalAcademicPeriods)
                        .totalActivePeriods(totalActivePeriods)
                        .build())
                .build();
    }
}
