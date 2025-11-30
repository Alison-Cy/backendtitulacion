package ec.yavirac.yavigestion.modules.auth.entities;

import ec.yavirac.yavigestion.modules.administration.enums.BloodType;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Person {
    @Id
    private Long id;
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
