package hd_world.hd_world_hello.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PatientVisit")
public class PatientVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Column(nullable = false, length = 10)
    private String visitStatusCode;

    // Getters and setters...
}