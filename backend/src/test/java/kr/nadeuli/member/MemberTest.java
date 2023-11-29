package kr.nadeuli.member;

import java.util.Optional;
import kr.nadeuli.common.Role;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.SmsDTO;
import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.service.jwt.AuthenticationService;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.member.impl.MemberRepository;
import kr.nadeuli.sms.SmsService;
import kr.nadeuli.sms.impl.SmsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  AuthenticationService authenticationService;

  @Autowired
  SmsService smsService;

  @Autowired
  SmsRepository smsRepository;

  @Test
  public void testAddMember() throws Exception{
    MemberDTO memberDTO = MemberDTO.builder()
        .affinity(50L)
        .cellphone("01089114554")
        .nickname("김엉윤")
        .dongNe("")
        .picture("")
        .nadeuliPayBalance(0L)
        .isActivate(false)
        .isNadeuliDelivery(false)
        .gu("")
        .role(Role.USER)
        .build();
    System.out.println(memberDTO);

//    authenticationService.addMember(memberDTO);
    System.out.println(authenticationService.addMember(memberDTO));
  }


  //@Test
  public void testLogin() throws Exception {
    String cellphone = "01086258914";

    Optional<MemberDTO> existingMember = memberRepository.findByCellphone(cellphone)
        .map(memberMapper::memberToMemberDTO);

    if (existingMember.isPresent()) {
      smsService.sendOne(cellphone);
      System.out.println(authenticationService.login(cellphone));
    } else {
      // 회원이 존재하지 않을 경우 예외 처리 또는 다른 로직을 추가할 수 있습니다.
      System.out.println("존재하지 않는 회원입니다.");
    }
  }

}
