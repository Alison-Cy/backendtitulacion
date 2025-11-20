package ec.yavirac.yavigestion.modules.administration.entities.projects;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProyecto;
    private String direccionEmpresa;
    private String plazoEjecucion;

    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    @Column(length = 2000)
    private String informeFinal;
}
