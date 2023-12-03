package kr.nadeuli.service.orikkiri;

import kr.nadeuli.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriScheMenChatFavRepository extends JpaRepository<OriScheMemChatFav, Long> {

    Page<OriScheMemChatFav> findByOrikkiri(Orikkiri orikkiri, Pageable pageable);

    Page<OriScheMemChatFav> findByOrikkiriSchedule(OrikkiriSchedule orikkiriSchedule, Pageable pageable);

    Page<OriScheMemChatFav> findByAnsQuestions(AnsQuestion ansQuestion, Pageable pageable);

    Page<OriScheMemChatFav> findByMemberAndOrikkiriNotNull(Member member, Pageable pageable);

    void deleteByMemberAndOrikkiri(Member member, Orikkiri orikkiriId);

}
