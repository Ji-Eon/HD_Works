package hd_world.hd_world_hello;

import hd_world.hd_world_hello.domain.Hospital;
import hd_world.hd_world_hello.domain.Patient;
import hd_world.hd_world_hello.repository.HospitalRepository;
import hd_world.hd_world_hello.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTests {


    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    private Hospital hospital;

    @BeforeEach
    public void setUp() {
        // given
        hospital = new Hospital();
        hospital.setHospitalName("정션병원");
        hospital.setMedicalInstitutionNumber("JB1232");
        hospital.setDirectorName("김지언");
        hospital = hospitalRepository.save(hospital);
    }




    @Test
    public void whenSavePatient_thenRetrievePatient() {
        // given
        Hospital hospital = new Hospital();
        // hospital.setId(1L); // In real scenarios, this is set by the database unless you are using DTOs or setting manually.
        // hospital의 다른 필드를 설정하는 로직 추가.

        Patient patient = new Patient();
        patient.setPatientName("홍길동");
        patient.setPatientId("910101-1234567");
        patient.setBirthday(new GregorianCalendar(1991, Calendar.OCTOBER, 22).getTime());
        patient.setPhone("010-1234-5678");
        patient.setGender("M");
        patient.setHospital(hospital); // 실제 테스트에서는 이미 저장되어 있는 hospital을 사용해야 합니다.

        // when
        patientRepository.save(patient);

        // then
        Patient retrievedPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient.getPatientName()).isEqualTo(patient.getPatientName());
        assertThat(retrievedPatient.getPatientId()).isEqualTo(patient.getPatientId());
        assertThat(retrievedPatient.getPhone()).isEqualTo(patient.getPhone());
        assertThat(retrievedPatient.getGender()).isEqualTo(patient.getGender());
        // Birthday 필드는 Date 타입이므로, 실제 날짜만 일치하는지 확인해야 할 수도 있습니다.
    }
}
