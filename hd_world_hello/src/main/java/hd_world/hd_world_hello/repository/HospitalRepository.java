package hd_world.hd_world_hello.repository;

import hd_world.hd_world_hello.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository  extends JpaRepository<Hospital, Long> {
}
