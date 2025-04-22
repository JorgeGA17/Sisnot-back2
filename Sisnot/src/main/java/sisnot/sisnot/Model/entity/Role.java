package sisnot.sisnot.Model.entity;

import jakarta.persistence.*;
import lombok.Data;
import sisnot.sisnot.Model.enums.ERole;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ERole name;



}
