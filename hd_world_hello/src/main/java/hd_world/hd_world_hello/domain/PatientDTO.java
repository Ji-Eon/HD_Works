package hd_world.hd_world_hello.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientDTO {
    private Long id;
    private String patientName;
    private String patientId;
    private Date birthday;
    private String phone;
    private String gender;
    private Long hospitalId;
    private String hospitalName;
    // 기타 필요한 Hospital 필드 추가

    // 생성자와 Getter, Setter
}