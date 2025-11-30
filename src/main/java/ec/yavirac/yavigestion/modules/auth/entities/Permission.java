package ec.yavirac.yavigestion.modules.auth.entities;


import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Permission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    String status = StatusConst.ACTIVE;
}
