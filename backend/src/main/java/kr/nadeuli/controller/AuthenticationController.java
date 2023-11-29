package kr.nadeuli.controller;


import jakarta.servlet.http.HttpServletResponse;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.RefreshTokenDTO;
import kr.nadeuli.dto.TokenDTO;
import kr.nadeuli.service.jwt.AuthenticationService;
import kr.nadeuli.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  private final MemberService memberService;

  @PostMapping("/addMember")
  public TokenDTO addUser(@RequestBody MemberDTO memberDTO) throws Exception {

    log.info("/api/v1/auth/json/addMember : POST");
    log.info("addUser에서 받은 user는 {}",memberDTO);

    return authenticationService.addMember(memberDTO);
  }

  @PostMapping("/login")
  public TokenDTO login(@RequestBody MemberDTO memberDTO) throws Exception {

    log.info("/api/v1/auth/json/login : POST");
    log.info("login에서 받은 memberDTO는 {}",memberDTO);

    return authenticationService.accessToken(memberDTO);
  }

  @PostMapping("/refresh")
  public TokenDTO refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) throws Exception {

    log.info("/api/v1/auth/json/refresh : POST");
    log.info("refresh에서 받은 tokenDTO는 {}",refreshTokenDTO);

    return authenticationService.refreshToken(refreshTokenDTO);
  }

}
