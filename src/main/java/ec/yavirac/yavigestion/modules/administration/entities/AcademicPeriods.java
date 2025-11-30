package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "academic_periods")
@Getter
@Setter
public class AcademicPeriods {
    @Id
    private Long id;

    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany(mappedBy = "academicPeriods")
    private Set<Career> careers;

    private String status = StatusConst.ACTIVE;
}
