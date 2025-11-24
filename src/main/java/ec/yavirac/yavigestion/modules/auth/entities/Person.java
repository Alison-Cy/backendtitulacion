package ec.yavirac.yavigestion.modules.auth.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

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
    private String bloodtype;
    private String gender;
    private String birthdate;

    @OneToOne
    private User user;
}
