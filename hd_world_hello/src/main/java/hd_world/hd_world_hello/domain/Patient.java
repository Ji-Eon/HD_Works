package hd_world.hd_world_hello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hd_world.hd_world_hello.repository.HospitalRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Getter // 모든 필드에 대한 getter를 자동으로 생성합니다.
@Setter // 모든 필드에 대한 setter를 자동으로 생성합니다.
@NoArgsConstructor // 기본 생성자를 자동으로 생성합니다.
@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Setter
    @Column(nullable = false, length = 45)
    private String patientName;

    @Column(nullable = false, length = 45)
    private String patientId;


    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, length = 13)
    private String phone;

    @Column(nullable = false, length = 13)
    private String gender;




}