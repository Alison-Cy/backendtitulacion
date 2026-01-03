package ec.yavirac.yavigestion.modules.administration.services.facades.stats;

import ec.yavirac.yavigestion.modules.administration.dtos.response.DashboardStatsDTO;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public interface StatsFacade {
    public GenericResponse<DashboardStatsDTO> getDashboardStats();
}
