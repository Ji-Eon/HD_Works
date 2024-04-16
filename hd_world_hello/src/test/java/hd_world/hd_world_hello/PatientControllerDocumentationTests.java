package hd_world.hd_world_hello;

import hd_world.hd_world_hello.domain.Hospital;
import hd_world.hd_world_hello.repository.HospitalRepository;
import hd_world.hd_world_hello.repository.PatientRepository;
import hd_world.hd_world_hello.request.PatientController;
import hd_world.hd_world_hello.domain.Patient;
import hd_world.hd_world_hello.domain.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(PatientController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PatientControllerDocumentationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private HospitalRepository hospitalRepository;

    @BeforeEach
    public void setUp() {
        // 공통 설정, MockBeans 등을 설정할 수 있습니다.
        // 예를 들어, 공통으로 사용되는 객체나 응답을 미리 정의할 수 있습니다.
    }

    @Test
    public void listPatientsExample() throws Exception {
        // patientRepository.findAll()을 호출했을 때 빈 리스트를 반환하도록 합니다.
        given(patientRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/patients/list"))
                .andExpect(status().isOk())
                .andDo(document("patients-list")); // patients-list 엔드포인트 문서화
    }

    @Test
    public void getPatientsPageWithDTOExample() throws Exception {
        // patientRepository.findAll(Pageable)을 호출했을 때 빈 페이지를 반환하도록 합니다.
        given(patientRepository.findAll(any(Pageable.class))).willReturn(Page.empty());

        mockMvc.perform(get("/patients/pagenation")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andDo(document("patients-pagenation")); // patients-pagenation 엔드포인트 문서화
    }

    @Test
    public void addPatientExample() throws Exception {
        // Hospital 객체를 생성하고 모킹합니다.
        Hospital mockHospital = new Hospital();
        mockHospital.setId(1L);
        mockHospital.setHospitalName("정션병원");
        given(hospitalRepository.findByHospitalName("정션병원")).willReturn(Optional.of(mockHospital));

        // Patient 객체를 생성합니다. 이 객체는 리퀘스트 바디의 예시를 나타냅니다.
        Patient mockPatient = new Patient();
        mockPatient.setPatientName("홍길동");
        mockPatient.setHospital(mockHospital); // 이 부분은 모킹된 병원을 설정합니다.

        // PatientRepository의 save를 모킹하여 만들어진 환자를 반환하도록 합니다.
        given(patientRepository.save(any(Patient.class))).willReturn(mockPatient);

        mockMvc.perform(post("/patients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patientName\": \"홍길동\", \"hospitalName\": \"정션병원\" }")) // 실제 JSON 요청 본문을 넣어주세요.
                .andExpect(status().isCreated())
                .andDo(document("patients-create")); // REST Docs에 patients-create 엔드포인트 문서화
    }

    @Test
    public void deletePatientExample() throws Exception {
        // patientRepository.deleteById()는 void를 반환하므로, 모킹할 필요가 없습니다.
        // Mockito.doNothing().when(patientRepository).deleteById(anyLong());

        mockMvc.perform(delete("/patients/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("patients-delete")); // patients-delete 엔드포인트 문서화
    }

    @Test
    public void getPatientByIdExample() throws Exception {
        // patientRepository.findById()를 호출했을 때 Optional.empty()를 반환하도록 모킹합니다.
        given(patientRepository.findById(any(Long.class))).willReturn(Optional.empty());

        mockMvc.perform(get("/patients/detail/{id}", 1L))
                .andExpect(status().isNotFound())
                .andDo(document("patients-detail")); // patients-detail 엔드포인트 문서화
    }

    @Test
    public void updatePatientExample() throws Exception {
        // patientRepository.findById()를 호출했을 때 Optional.of()를 반환하도록 합니다.
        Patient existingPatient = new Patient();
        existingPatient.setId(1L);
        existingPatient.setPatientName("John Doe");

        given(patientRepository.findById(any(Long.class))).willReturn(Optional.of(existingPatient));
        given(patientRepository.save(any(Patient.class))).willReturn(existingPatient);

        mockMvc.perform(put("/patients/modify/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"patientName\": \"John Updated\"}")) // 여기에 JSON 요청 본문을 입력합니다.
                .andExpect(status().isOk())
                .andDo(document("patients-modify")); // patients-modify 엔드포인트 문서화
    }

    @Test
    public void searchPatientsExample() throws Exception {
        // patientRepository.findBy...()을 호출했을 때 빈 리스트를 반환하도록 합니다.
        given(patientRepository.findByPatientNameContaining(any(String.class))).willReturn(Collections.emptyList());
        given(patientRepository.findByPatientIdContaining(any(String.class))).willReturn(Collections.emptyList());
        given(patientRepository.findByBirthday(any(LocalDate.class))).willReturn(Collections.emptyList());

        mockMvc.perform(get("/patients/search")
                        .param("name", "John")
                        .param("id", "123456"))
                .andExpect(status().isOk())
                .andDo(document("patients-search")); // patients-search 엔드포인트 문서화
    }

}