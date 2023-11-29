package kr.nadeuli.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

  private final CustomUserDetailsService customUserDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
    // 각종 처리를 구현
    // 비번이 일치하는지
    System.out.println("SEXSEX");
    // 아이디로 회원을 조회 했을 때 존재하는 회원인지
    // 기타 등등과 적절한 예외 처리
    return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                                   userDetails.getAuthorities());
  }
}
