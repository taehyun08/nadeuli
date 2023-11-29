package kr.nadeuli.service.member.impl;


import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;

  private final MemberMapper memberMapper;


  // 새로운 태그 생성 및 유효성 검사 메소드
  public String generateUniqueTag() {
    String tag;

    do {
      // 랜덤한 네 자리 알파벳 대/소문자 또는 숫자 생성
      tag = generateRandomTag();
    } while (!isTagUnique(tag)); // 태그가 유니크한지 검사

    return "#" + tag; // "#"를 추가하여 반환
  }

  // 랜덤한 네 자리 알파벳 대/소문자 또는 숫자 생성 메소드
  public String generateRandomTag() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder tagBuilder = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      int index = (int) (Math.random() * characters.length());
      tagBuilder.append(characters.charAt(index));
    }

    return tagBuilder.toString();
  }

  // 태그가 유니크한지 검사하는 메소드
  public boolean isTagUnique(String tag) {
    return memberRepository.findByTag(tag).isEmpty();
  }
}

