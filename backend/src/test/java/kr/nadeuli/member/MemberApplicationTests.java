package kr.nadeuli.member;


import java.util.List;
import kr.nadeuli.category.Role;
import kr.nadeuli.dto.GpsDTO;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.dto.SmsDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.service.jwt.AuthenticationService;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.member.MemberRepository;
import kr.nadeuli.service.sms.SmsService;
import kr.nadeuli.service.sms.SmsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemberApplicationTests {

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

  @Value("${pageSize}")
  private int pageSize;

  @DisplayName("자체회원가입 테스트")
  //@Test
  public void testAddMember() throws Exception {
    //입력받는 값
    String nickname = "김영진";
    String cellPhone = "01088889999";

    SmsDTO smsDTO = SmsDTO.builder()
        .authNumber("11111")
        .cellphone(cellPhone)
        .build();

    // 1. 휴대폰 번호 인증 후 로그인이 가능하기때문에 SmsService의 목 객체(mock) 생성
    SmsService mockSmsService = Mockito.mock(SmsService.class);

    // 2. 인증번호 확인 메소드인 verifySms()가 항상 true를 반환하도록 설정
    Mockito.when(mockSmsService.verifySms(smsDTO)).thenReturn(true);

    // 3. 인증번호 확인이 완료되었다면...
    if (mockSmsService.verifySms(smsDTO)) {
      MemberDTO memberDTO = MemberDTO.builder()
          .affinity(50L)
          .cellphone(cellPhone)
          .nickname(nickname)
          .dongNe("서울특별시 강서구 공항동")
          .picture("empty.jpg")
          .nadeuliPayBalance(0L)
          .isActivate(false)
          .isNadeuliDelivery(false)
          .gu("")
          .role(Role.USER)
          .build();

      System.out.println(authenticationService.addMember(memberDTO));
    } else {
      System.out.println("휴대폰 인증을 완료해야합니다..");
    }

  }

  @DisplayName("자체로그인 테스트")
  //  @Test
  public void testLogin() throws Exception {
    //입력받는 값
    String cellphone = "01086258914";

    SmsDTO smsDTO = SmsDTO.builder()
        .authNumber("11111")
        .cellphone(cellphone)
        .build();

    // 1. 휴대폰 번호 인증 후 로그인이 가능하기때문에 SmsService의 목 객체(mock) 생성
    SmsService mockSmsService = Mockito.mock(SmsService.class);

    // 2. 인증번호 확인 메소드인 verifySms()가 항상 true를 반환하도록 설정
    Mockito.when(mockSmsService.verifySms(smsDTO)).thenReturn(true);

    // 3. 인증번호 확인이 완료되었다면...
    if (mockSmsService.verifySms(smsDTO)) {
      System.out.println(authenticationService.login(cellphone));
    } else {
      System.out.println("존재하지 않는 회원입니다.");
    }
  }

  @DisplayName("동네설정 테스트")
//  @Test
  public void testGetDongNe() throws Exception {
    // 실제 현재 위치 좌표는 Js의 geolocation 사용 예정
    double x = 127.0292881;
    double y = 37.4923615;

    GpsDTO gpsDTO = new GpsDTO();
    gpsDTO.setX(x);
    gpsDTO.setY(y);

    String address = memberService.getDongNe(gpsDTO);
    System.out.println("주소: " + address);
  }

  @DisplayName("내프로필조회 테스트")
//  @Test
  public void testGetMember() throws Exception{
    String tag="#4FzB";
    MemberDTO memberDTO = memberService.getMember(tag);
    System.out.println(memberDTO);
  }

  @DisplayName("상대프로필조회 테스트")
  @Test
  public void testGetOtherMember() throws Exception{
    String tag="#4FzB";
    MemberDTO memberDTO = memberService.getOtherMember(tag);
    System.out.println(memberDTO);
  }

  @DisplayName("회원목록조회 테스트")
//  @Test
  public void testGetMemberList() throws Exception{
    SearchDTO searchDTO = SearchDTO.builder()
        .currentPage(0)
        .searchKeyword("엄")
        .pageSize(pageSize)
        .build();

    List<MemberDTO> memberDTOList = memberService.getMemberList(searchDTO);
    System.out.println(memberDTOList);

  }
}