package kr.nadeuli.service.jwt;

import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.RefreshTokenDTO;
import kr.nadeuli.dto.TokenDTO;

public interface AuthenticationService {

  public TokenDTO addMember(MemberDTO memberDTO) throws Exception;

  public TokenDTO login(String cellphone) throws Exception;

  public TokenDTO accessToken(MemberDTO memberDTO) throws Exception ;

  public TokenDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws Exception ;


}
