package kr.nadeuli.service.orikkiri;

import kr.nadeuli.entity.Orikkiri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrikkiriRepository extends JpaRepository<Orikkiri, Long> {
}
