package ec.yavirac.yavigestion.modules.administration.controllers;

import ec.yavirac.yavigestion.modules.administration.dtos.response.DashboardStatsDTO;
import ec.yavirac.yavigestion.modules.administration.services.facades.stats.StatsFacade;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Qualifier("statsFacadeImpl")
    private final StatsFacade statsFacade;

    public StatsController( StatsFacade statsFacade) {
        this.statsFacade = statsFacade;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<GenericResponse<DashboardStatsDTO>> findDashboardStats() {
        GenericResponse<DashboardStatsDTO> response = statsFacade.getDashboardStats();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
