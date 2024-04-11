package hd_world.hd_world_hello.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String hospitalName;

    @Column(nullable = false, length = 20)
    private String medicalInstitutionNumber;

    @Column(nullable = false, length = 10)
    private String directorName;

    // Getters and setters...
}