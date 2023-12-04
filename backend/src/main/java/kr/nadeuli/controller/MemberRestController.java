package kr.nadeuli.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {


//  @GetMapping
//  public ResponseEntity<String> sayHello(){
//    return ResponseEntity.ok("반갑습니다 회원님");
//  }

//  @GetMapping("/google")
//  public Map<String, Object> getUser(@AuthenticationPrincipal
//  OAuth2User oAuth2User){
//
//    return oAuth2User.getAttributes();
//
//  }

  @PostMapping
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
}
