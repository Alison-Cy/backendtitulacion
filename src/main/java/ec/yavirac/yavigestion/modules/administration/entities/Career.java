package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "careers")
@Getter
@Setter
public class Career {
    @Id
    private Long id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "career_period",
            joinColumns = @JoinColumn(name = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "period_id")
    )
    private List<AcademicPeriods> academicPeriods;


    private String status = StatusConst.ACTIVE;
}
