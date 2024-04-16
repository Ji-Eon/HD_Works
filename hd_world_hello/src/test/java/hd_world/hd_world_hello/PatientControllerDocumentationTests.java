package hd_world.hd_world_hello;

import hd_world.hd_world_hello.repository.HospitalRepository;
import hd_world.hd_world_hello.repository.PatientRepository;
import hd_world.hd_world_hello.request.PatientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    }

    @Test
    public void listPatientsExample() throws Exception {
        // 이 부분에서 patientRepository의 반환값을 모킹합니다.
        // 예시:
        // when(patientRepository.findAll()).thenReturn(...);

        mockMvc.perform(get("/patients/list"))
                .andExpect(status().isOk())
                .andDo(document("patients-list")); // 이 부분에서 REST Docs 문서화가 진행됩니다.
    }

    // 다른 엔드포인트에 대한 테스트를 계속 추가하실 수 있습니다.
}
