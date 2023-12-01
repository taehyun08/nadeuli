package kr.nadeuli.service.orikkiri;

import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.OriScheMemChatFav;
import kr.nadeuli.entity.Orikkiri;
import kr.nadeuli.entity.OrikkiriSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriScheMenChatFavRepository extends JpaRepository<OriScheMemChatFav, Long> {

    Page<OriScheMemChatFav> findByOrikkiri(Orikkiri orikkiri, Pageable pageable);

    Page<OriScheMemChatFav> findByOrikkiriSchedule(OrikkiriSchedule orikkiriSchedule, Pageable pageable);

    void deleteByMemberAndOrikkiri(Member build, Orikkiri build1);
}
