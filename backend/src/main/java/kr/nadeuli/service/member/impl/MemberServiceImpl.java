package kr.nadeuli.service.member.impl;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.nadeuli.dto.AddressDTO;
import kr.nadeuli.dto.BlockDTO;
import kr.nadeuli.dto.GpsDTO;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Block;
import kr.nadeuli.entity.Member;
import kr.nadeuli.mapper.BlockMapper;
import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.service.member.MemberRepository;
import kr.nadeuli.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;


@RequiredArgsConstructor
@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;

  private final MemberMapper memberMapper;

  private final BlockMapper blockMapper;

  private final WebClient.Builder webClientBuilder;

  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  String mapKey;

  @Value("${kakao.map.api-url}")
  private String mapApiUrl;

  // 새로운 태그 생성 및 유효성 검사 메소드
  public String addTag() {
    String tag;

    do {
      // 랜덤한 네 자리 알파벳 대/소문자 또는 숫자 생성
      tag = generateRandomTag();
    } while (!isTagUnique(tag)); // 태그가 유니크한지 검사

    return "#" + tag; // "#"를 추가하여 반환
  }

  // 랜덤한 네 자리 알파벳 대/소문자 또는 숫자 생성 메소드
  public String generateRandomTag() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder tagBuilder = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      int index = (int) (Math.random() * characters.length());
      tagBuilder.append(characters.charAt(index));
    }

    return tagBuilder.toString();
  }

  // 태그가 유니크한지 검사하는 메소드
  public boolean isTagUnique(String tag) {
    return memberRepository.findByTag(tag).isEmpty();
  }

  @Override
  public void updateMember(MemberDTO memberDTO) throws Exception {

  }

  @Override
  public MemberDTO getMember(String tag) throws Exception {
    return memberRepository.findByTag(tag).map(memberMapper::memberToMemberDTO).orElse(null);
  }

  @Override
  public MemberDTO getOtherMember(String tag) throws Exception {
    //상대프로필은 프로필사진, 닉네임, 태그, 동네, 친화력이있어야한다.
    Member member = memberRepository.findByTag(tag).orElse(null);

    if(member != null){
      return MemberDTO.builder()
          .picture(member.getPicture())
          .nickname(member.getNickname())
          .tag(member.getTag())
          .dongNe(member.getDongNe())
          .affinity(member.getAffinity())
          .build();
    }
    return null;
  }

  @Override
  public List<MemberDTO> getMemberList(SearchDTO searchDTO) throws Exception {
    Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize());
    Page<Member> memberPage;
    if(searchDTO.getSearchKeyword() == null || searchDTO.getSearchKeyword().isEmpty()){
      memberPage = memberRepository.findAll(pageable);
    }else {
      memberPage = memberRepository.findByNicknameContainingOrTagContaining(searchDTO.getSearchKeyword(), searchDTO.getSearchKeyword(), pageable);
    }
    log.info(memberPage);
    return memberPage.map(memberMapper::memberToMemberDTO).toList();
  }

  @Override
  public String getDongNe(GpsDTO gpsDTO) throws Exception {
    WebClient webClient = webClientBuilder.baseUrl(mapApiUrl)
        .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + mapKey)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("x", String.valueOf(gpsDTO.getX()));
    queryParams.put("y", String.valueOf(gpsDTO.getY()));

    // Map을 MultiValueMap으로 변환
    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.setAll(queryParams);

    String kakaoMapAddress = webClient.get()
        .uri(uriBuilder -> uriBuilder.queryParams(multiValueMap).build())
        .retrieve()
        .bodyToMono(String.class)
        .block();

    // Jackson ObjectMapper를 사용하여 JSON을 AddressDTO 객체로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    AddressDTO addressDTO = objectMapper.readValue(kakaoMapAddress, AddressDTO.class);

    log.info("WebClient를 이용한 주소 검색 결과: {}", addressDTO);

    return addressDTO.getDocuments().get(1).getAddressName();
  }

  @Override
  public void addDisableMember(BlockDTO blockDTO, String tag) throws Exception {
    log.info("받은 BlockDTO는: {}", blockDTO);
    log.info("받은 tag는: {}", tag);

    MemberDTO memberDTO = getMember(tag);

    blockDTO.setBlockMember(memberDTO);

    Block block = blockMapper.blockDTOToBlock(blockDTO);


  }

  @Override
  public void deleteDisableMember(String tag) throws Exception {

  }
}

