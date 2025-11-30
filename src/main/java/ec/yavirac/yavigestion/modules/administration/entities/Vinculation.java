package ec.yavirac.yavigestion.modules.administration.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "vinculation")
public class Vinculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razonSocial;
    private String representanteLegal;
    private String tutorEmpresarial;
    private String direccion;
    private String telefono;
    private String email;
    private String provincia;
    private String canton;
    private String parroquia;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}