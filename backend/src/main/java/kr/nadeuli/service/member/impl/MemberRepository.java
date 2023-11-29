package kr.nadeuli.service.member.impl;

import java.util.Optional;
import kr.nadeuli.common.Role;

import kr.nadeuli.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  Optional<Member> findByEmail(String email);

  Optional<Member> findByRole(Role role);

  Optional<Member> findByCellphone(String cellphone);

  Optional<Member> findByTag(String tag);
  Optional<Member> findBySocialId(String SocialId);


}
