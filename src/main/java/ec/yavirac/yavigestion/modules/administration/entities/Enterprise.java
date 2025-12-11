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
@Table(name = "enterprises")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Enterprise {
    @Id
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDate updatedAt;

    private String name;
    private String description;
    private String ruc;
    private String address;
    private String telephone;
    private String email;

    private String status = StatusConst.ACTIVE;
}
