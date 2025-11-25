package ec.yavirac.yavigestion.modules.administration.services.enterprises;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enterprises", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruc")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 13, nullable = false)
    private String ruc;

    private String address;
    private String phones;
    private String email;
    private String contactPerson;
    private String economicSector;

    private boolean active = true; 

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
