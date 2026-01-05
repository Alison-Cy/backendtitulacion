package ec.yavirac.yavigestion.modules.administration.entities;

import ec.yavirac.yavigestion.modules.administration.enums.CareerType;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "careers")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String name;
    private String description;
    private CareerType type;


    @ManyToMany(mappedBy = "careers")
    private List<AcademicPeriods> academicPeriods;


    private String status = StatusConst.ACTIVE;
}
