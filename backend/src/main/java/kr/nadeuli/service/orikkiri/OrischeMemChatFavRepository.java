package kr.nadeuli.service.orikkiri;


import kr.nadeuli.entity.OriScheMemChatFav;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrischeMemChatFavRepository extends JpaRepository<OriScheMemChatFav, Long> {

//    Page<OriScheMemChatFav> findByOriScheMemChatFavId();
}
