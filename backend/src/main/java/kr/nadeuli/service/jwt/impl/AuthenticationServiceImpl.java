package kr.nadeuli.service.jwt.impl;

import java.util.HashMap;
import java.util.Optional;
import kr.nadeuli.common.Role;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.RefreshTokenDTO;
import kr.nadeuli.dto.TokenDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.security.CustomAuthenticationManager;
import kr.nadeuli.security.CustomAuthenticationToken;
import kr.nadeuli.service.jwt.AuthenticationService;
import kr.nadeuli.service.jwt.JWTService;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.member.impl.MemberRepository;
import kr.nadeuli.sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {


  private final MemberMapper memberMapper;

  private final CustomAuthenticationManager authenticationManager;

  private final JWTService jwtService;

  private final MemberRepository memberRepository;

  private final MemberService memberService;

  private final SmsService smsService;

  public TokenDTO addMember(MemberDTO memberDTO) throws Exception {
    // findByCellphone로 회원을 찾음
    Optional<MemberDTO> existingMember = memberRepository.findByCellphone(memberDTO.getCellphone())
        .map(memberMapper::memberToMemberDTO);
    // 회원이 이미 존재하는 경우 예외를 던짐

    if (existingMember.isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 회원입니다. 회원의 정보: " + existingMember.get());
    }else{
      // 회원이 존재하지 않는 경우
      memberDTO.setTag(memberService.generateUniqueTag());
      memberDTO.setRole(Role.USER);
      Member member = memberMapper.memberDTOToMember(memberDTO);
      memberRepository.save(member);
      MemberDTO existMember = memberRepository.findByTag(memberDTO.getTag()).map(memberMapper::memberToMemberDTO).orElseThrow(()-> new IllegalArgumentException("없는 태그입니다."));
      return accessToken(existMember);
    }
  }

  public TokenDTO login(String cellphone) throws Exception {
    // findByCellphone로 회원을 찾음
    Optional<MemberDTO> existingMember = memberRepository.findByCellphone(cellphone)
        .map(memberMapper::memberToMemberDTO);

    // 회원이 존재할 경우 jwt발급
    if (existingMember.isPresent()) {
      smsService.sendOne(cellphone);

      MemberDTO existMember = existingMember.get();
      return accessToken(existMember);
    }else{
      // 회원이 존재하지 않을 경우 예외를 던짐
      throw new IllegalArgumentException("존재하지않는 회원입니다.");
    }
  }

  @Override
  public TokenDTO accessToken(MemberDTO memberDTO) throws Exception {
    CustomAuthenticationToken authRequest = new CustomAuthenticationToken(memberDTO.getTag());
    // CustomAuthenticationToken을 사용하려면 CustomAuthenticationManager의 authenticate가 호출되도록 해야 합니다.
    // 따라서 여기에서는 CustomAuthenticationManager를 직접 호출하게 됩니다.
    authenticationManager.authenticate(authRequest);
    Member member = memberRepository.findByTag(memberDTO.getTag()).orElseThrow(()-> new IllegalArgumentException("없는 태그입니다."));
    TokenDTO tokenDTO = new TokenDTO();

    tokenDTO.setAccessToken(jwtService.generateToken(member));
    tokenDTO.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(), member));

    return tokenDTO;
  }

  @Override
  public TokenDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws Exception  {
    String memberTag = jwtService.extractUserName(refreshTokenDTO.getRefreshToken());
    Member member = memberRepository.findByTag(memberTag).orElseThrow(()-> new IllegalArgumentException("없는 태그입니다."));
    if(jwtService.isTokenValid(refreshTokenDTO.getRefreshToken(),member)){

      TokenDTO token = new TokenDTO();
      token.setAccessToken(jwtService.generateToken(member));
      token.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(),member)); // 새로운 리프레시 토큰 설정

      return token;
    }

    return null;
  }


}
