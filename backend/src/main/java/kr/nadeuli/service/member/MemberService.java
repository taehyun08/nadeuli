package kr.nadeuli.service.member;

import jakarta.servlet.http.HttpServletResponse;
import kr.nadeuli.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService {
  //jwt Authentication
//  public void addMember(MemberDTO memberDTO, HttpServletResponse response) throws Exception ;


  public boolean isTagUnique(String tag) throws Exception;

  public String generateRandomTag() throws Exception;

  public String generateUniqueTag() throws Exception;

}
