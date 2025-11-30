package ec.yavirac.yavigestion.modules.administration.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enterprises")
@Getter
@Setter
public class Enterprise {
    @Id
    private Long id;
    private String name;
    private String description;
    private String ruc;
    private String address;
    private String telephone;
    private String email;
}
