package ec.yavirac.yavigestion.modules.auth.entities;

import ec.yavirac.yavigestion.modules.administration.enums.BloodType;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Person {
    @Id
    private Long id;
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDate createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDate updatedAt;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phonenumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private BloodType bloodtype;
    private String gender;
    private LocalDate birthdate;

    @OneToOne(mappedBy = "person")
    private User user;

    String status = StatusConst.ACTIVE;
}
