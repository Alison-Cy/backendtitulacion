package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Interships {
    @Id
    private Long id;

    // TODO: Terminar las relaciones
    private Set<User> users;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    private String status = StatusConst.ACTIVE;
}
