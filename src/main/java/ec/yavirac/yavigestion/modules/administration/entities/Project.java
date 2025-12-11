package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDate updatedAt;

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
