package kr.nadeuli.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberRestController {

  private final MemberService memberService;

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
    // SecurityContext에서 Authentication을 가져와서 로그아웃
    SecurityContextHolder.clearContext();

    // 쿠키 삭제
    Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, null);
    cookie.setPath("/");
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    // 헤더에서도 삭제
    response.setHeader(HttpHeaders.AUTHORIZATION, "");

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/getMember/{tag}")
  public MemberDTO getMember(@PathVariable String tag) throws Exception{
    log.info("getMember에서 받은 tag는 {}",tag);

    return memberService.getMember(tag);

  }
}
