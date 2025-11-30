package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {
    @Id
    private Long id;
    private String name;
    private String address;
    @Column(name = "execution_term")
    private String executionTerm;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(length = 2000, name = "final_report")
    private String finalReport;

    // TODO: Terminar las relaciones
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    private String status = StatusConst.ACTIVE;
}
