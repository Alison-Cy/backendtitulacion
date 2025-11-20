package ec.yavirac.yavigestion.modules.administration.entities.vinculation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vinculation")
public class Vinculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razonSocial;
    private String representanteLegal;
    private String tutorEmpresarial; // Cargo
    private String direccion;
    private String telefono;
    private String email;
    private String provincia;
    private String canton;
    private String parroquia;
}