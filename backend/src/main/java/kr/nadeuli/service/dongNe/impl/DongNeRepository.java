package kr.nadeuli.service.dongNe.impl;

import kr.nadeuli.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DongNeRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
