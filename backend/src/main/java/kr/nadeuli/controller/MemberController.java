package kr.nadeuli.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  @GetMapping
  public ResponseEntity<String> sayHello(){
    return ResponseEntity.ok("반갑습니다 회원님");
  }

  @GetMapping("/google")
  public Map<String, Object> getUser(@AuthenticationPrincipal
  OAuth2User oAuth2User){

 return oAuth2User.getAttributes();

  }

}
