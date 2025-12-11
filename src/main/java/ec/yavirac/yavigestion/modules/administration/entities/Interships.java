package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Interships {
    @Id
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDate updatedAt;

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
