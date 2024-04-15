package hd_world.hd_world_hello.repository;

import hd_world.hd_world_hello.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAll(Pageable pageable);
    List<Patient> findByPatientNameContaining(String name);
    List<Patient> findByPatientIdContaining(String id);
    List<Patient> findByBirthday(Date birthday);


}
