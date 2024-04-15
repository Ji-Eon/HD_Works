package hd_world.hd_world_hello.request;


import hd_world.hd_world_hello.domain.Patient;
import hd_world.hd_world_hello.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients") // 이 경로로 시작하는 모든 HTTP 요청을 처리합니다.
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public List<Patient> findAllUser() {
        return patientRepository.findAll();
    }

    @GetMapping("/pagenation")
    public Page<Patient> getPatientsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findAll(pageable);
    }

//    @PostMapping("/user") // Patient 테이블에 데이터를 입력하는 부분 insert into user (msrl, name, uid) values (null, ?, ?) 와 같음
//    public Patient save() {
//        Patient user = Patient.builder()
//                .uid("pepe@sadfrog.com") // Patient 클래스에서 만들어진 변수 uid, name
//                .name("페페")
//                .build();
//
//        return userJpaRepo.save(user);
//    }

}
