package hd_world.hd_world_hello.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 13)
    private String patientRegistrationNumber;

    // Getters and setters...
}