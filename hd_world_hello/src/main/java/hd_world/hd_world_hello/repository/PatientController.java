package hd_world.hd_world_hello.repository;



import hd_world.hd_world_hello.domain.Hospital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import hd_world.hd_world_hello.domain.Patient;
import hd_world.hd_world_hello.domain.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    private HospitalRepository hospitalRepository;

    public PatientController(PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    @GetMapping("/list")
    public List<PatientDTO> findAllUserWithDTO() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO dto = new PatientDTO();
            dto.setId(patient.getId());
            dto.setPatientName(patient.getPatientName());
            dto.setPatientId(patient.getPatientId());
            dto.setBirthday(patient.getBirthday());
            dto.setPhone(patient.getPhone());
            dto.setGender(patient.getGender());

            // 여기에서 Lazy 로딩으로 인해 hospital이 프록시일 수 있으므로,
            // 실제 필요한 정보만을 DTO에 넣습니다.
            if (patient.getHospital() != null) {
                dto.setHospitalId(patient.getHospital().getId());
                dto.setHospitalName(patient.getHospital().getHospitalName()); // 실제로 필요한 병원 이름
            }

            patientDTOs.add(dto);
        }

        return patientDTOs;
    }
    @GetMapping("/pagenation")
    public Page<PatientDTO> getPatientsPageWithDTO(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.findAll(pageable);

        Page<PatientDTO> dtoPage = patientPage.map(patient -> {
            PatientDTO dto = new PatientDTO();
            dto.setId(patient.getId());
            dto.setPatientName(patient.getPatientName());
            dto.setPatientId(patient.getPatientId());
            dto.setBirthday(patient.getBirthday());
            dto.setPhone(patient.getPhone());
            dto.setGender(patient.getGender());

            if (patient.getHospital() != null) {
                dto.setHospitalId(patient.getHospital().getId());
                dto.setHospitalName(patient.getHospital().getHospitalName());
            }

            return dto;
        });

        return dtoPage;
    }


    @PostMapping("/create")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        System.out.println("Adding new patient:");
        System.out.println("Name: " + patient.getPatientName());

        Optional<Hospital> hospital = hospitalRepository.findByHospitalName("정션병원");
        if (hospital.isPresent()) {
            patient.setHospital(hospital.get());
        } else {
            System.out.println("Hospital '정션병원' not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hospital not found");
        }

        // Save the patient
        Patient newPatient = patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient created with ID: " + newPatient.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("환자를 찾을 수 없습니다.");
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found for this id :: " + id
                ));

        patient.setPatientName(patientDetails.getPatientName());
        patient.setPatientId(patientDetails.getPatientId());
        patient.setBirthday(patientDetails.getBirthday());
        patient.setPhone(patientDetails.getPhone());
        patient.setGender(patientDetails.getGender());
        // 필요하다면 여기에 더 많은 필드를 추가할 수 있습니다.

        final Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDTO>> searchPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam(required = false) String hospitalName) {

        List<Patient> patients = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            patients.addAll(patientRepository.findByPatientNameContaining(name));
        }
        if (id != null && !id.isEmpty()) {
            patients.addAll(patientRepository.findByPatientIdContaining(id));
        }
        if (birthday != null) {
            patients.addAll(patientRepository.findByBirthday(birthday));

        }

        // 중복 제거 로직 추가
        Set<Patient> uniquePatients = new HashSet<>(patients);
        List<Patient> uniquePatientList = new ArrayList<>(uniquePatients);

        // DTO로 변환
        List<PatientDTO> patientDTOs = uniquePatientList.stream()
                .map(patient -> {
                    PatientDTO dto = new PatientDTO();
                    dto.setId(patient.getId());
                    dto.setPatientName(patient.getPatientName());
                    dto.setPatientId(patient.getPatientId());
                    dto.setBirthday(patient.getBirthday());
                    dto.setPhone(patient.getPhone());
                    dto.setGender(patient.getGender());

                    // 병원 정보가 있는 경우 병원 이름을 설정
                    if (patient.getHospital() != null) {
                        dto.setHospitalName(patient.getHospital().getHospitalName());
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(patientDTOs);
    }

}