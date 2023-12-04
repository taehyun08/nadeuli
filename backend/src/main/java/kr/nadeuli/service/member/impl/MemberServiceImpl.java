package kr.nadeuli.service.member.impl;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.nadeuli.category.DeliveryState;
import kr.nadeuli.category.TradeType;
import kr.nadeuli.dto.AddressDTO;
import kr.nadeuli.dto.BlockDTO;
import kr.nadeuli.dto.GpsDTO;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.NadeuliDeliveryDTO;
import kr.nadeuli.dto.NadeuliPayHistoryDTO;
import kr.nadeuli.dto.OriScheMemChatFavDTO;
import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.ReportDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.OriScheMemChatFav;
import kr.nadeuli.entity.Product;
import kr.nadeuli.mapper.BlockMapper;
import kr.nadeuli.mapper.MemberMapper;
import kr.nadeuli.mapper.OriScheMemChatFavMapper;
import kr.nadeuli.mapper.ProductMapper;
import kr.nadeuli.mapper.ReportMapper;
import kr.nadeuli.scheduler.BlockScheduler;
import kr.nadeuli.service.member.BlockRepository;
import kr.nadeuli.service.member.MemberRepository;
import kr.nadeuli.service.member.MemberService;
import kr.nadeuli.service.member.ReportRepository;
import kr.nadeuli.service.orikkiri.OriScheMenChatFavRepository;
import kr.nadeuli.service.product.ProductService;
import kr.nadeuli.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
@Transactional
@RequiredArgsConstructor
@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;

  private final BlockRepository blockRepository;

  private final MemberMapper memberMapper;

  private final BlockMapper blockMapper;

  private final WebClient.Builder webClientBuilder;

  private final BlockScheduler blockDayScheduler;

  private final SmsService smsService;

  private final OriScheMenChatFavRepository oriScheMenChatFavRepository;

  private final OriScheMemChatFavMapper oriScheMemChatFavMapper;

  private final ProductService productService;

  private final ProductMapper productMapper;

  private final ReportRepository reportRepository;

  private final ReportMapper reportMapper;

  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  String mapKey;

  @Value("${affinity}")
  String affinity;

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

  //회원 프로필 수정
  @Override
  public void updateMember(MemberDTO memberDTO) throws Exception {
   log.info("받은 member는{}",memberDTO);

   MemberDTO existMember = getMember(memberDTO.getTag());

    // Null이 아닌 값만 수정
    if (memberDTO.getPicture() != null) {
      log.info("받은 Picture는{}",memberDTO.getPicture());
      existMember.setPicture(memberDTO.getPicture());
    }
    if (memberDTO.getNickname() != null) {
      log.info("받은 Nickname는{}",memberDTO.getNickname());
      existMember.setNickname(memberDTO.getNickname());
    }
    if (memberDTO.getCellphone() != null) {
      log.info("받은 Cellphone는{}",memberDTO.getCellphone());
      existMember.setCellphone(memberDTO.getCellphone());
    }
    if (memberDTO.getEmail() != null) {
      log.info("받은 Email는{}",memberDTO.getEmail());
      existMember.setEmail(memberDTO.getEmail());
    }
    if (memberDTO.getDongNe() != null) {
      log.info("받은 DongNe는{}",memberDTO.getDongNe());
      existMember.setDongNe(memberDTO.getDongNe());
    }
    if (memberDTO.getGu() != null) {
      log.info("받은 Gu는{}",memberDTO.getGu());
      existMember.setGu(memberDTO.getGu());
    }
    if (memberDTO.getNadeuliPayBalance() != null) {
      log.info("받은 NadeuliPayBalance는{}",memberDTO.getNadeuliPayBalance());
      existMember.setNadeuliPayBalance(memberDTO.getNadeuliPayBalance());
    }

   memberRepository.save(memberMapper.memberDTOToMember(existMember));
  }


  //내 프로필 조회
  @Override
  public MemberDTO getMember(String tag) throws Exception {
    return memberRepository.findByTag(tag).map(memberMapper::memberToMemberDTO).orElse(null);

  }

  //상대 프로필 조회
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

  //회원 목록 조회
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

  //회원가입시 동네 설정, 동네 수정
  @Override
  public AddressDTO addDongNe(String tag, GpsDTO gpsDTO) throws Exception {
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

    // 존재하는 회원의 DongNe 업데이트
    MemberDTO existingMember = getMember(tag);
    if (existingMember != null) {
      existingMember.setDongNe(addressDTO.getDocuments().get(1).getAddressName());
      existingMember.setGu(addressDTO.getDocuments().get(1).getRegion2depthName());
      updateMember(existingMember);
    }

//    return addressDTO.getDocuments().get(1).getAddressName() + addressDTO.getDocuments().get(1).getRegion2depthName();
    return addressDTO;
  }

  //회원 정지
  @Override
  public void addBlockMember(BlockDTO blockDTO, String tag) throws Exception {
    log.info("받은 BlockDTO는: {}", blockDTO);
    log.info("받은 tag는: {}", tag);

    MemberDTO memberDTO = getMember(tag);

    // BlockEnd가 있는 경우에만 정지하지 않도록 조건 추가
    if (memberDTO.getBlockEnd() == null) {
      // Block의 값을 가져와서 지속적인 Join 방지
      memberDTO.setBlockDay(blockDTO.getBlockDay());
      memberDTO.setBlockEnd(blockDTO.getBlockEnd());
      memberDTO.setBlockReason(blockDTO.getBlockReason());

      updateMember(memberDTO);

      blockDTO.setBlockMember(memberDTO);

      blockRepository.save(blockMapper.blockDTOToBlock(blockDTO));

      blockDayScheduler.startBlockDayScheduler(tag);
    } else {
      log.info("이미 정지된 회원입니다.");
    }
  }

  //회원 정지 해제
  @Override
  public void deleteBlockMember(String tag) throws Exception {

    MemberDTO memberDTO = getMember(tag);

    memberDTO.setBlockDay(null);
    memberDTO.setBlockEnd(null);
    memberDTO.setBlockReason(null);
    updateMember(memberDTO);

  }

  //회원 비활성화 및 활성화
  @Override
  public void handleMemberActivate(String tag) throws Exception {
    log.info("tag는 {}", tag);

    MemberDTO memberDTO = getMember(tag);

    // 현재 상태가 true이면 false로, false이면 true로 변경
    memberDTO.setActivate(!getMember(tag).isActivate());

    memberRepository.save(memberMapper.memberDTOToMember(memberDTO));

  }

  //나드리부름 알림 비활성화 및 활성화
  @Override
  public void handleNadeuliDelivery(String tag) throws Exception {
    log.info("tag는 {}", tag);

    MemberDTO memberDTO = getMember(tag);

    // 현재 상태가 true이면 false로, false이면 true로 변경
    memberDTO.setNadeuliDelivery(!getMember(tag).isNadeuliDelivery());

    memberRepository.save(memberMapper.memberDTOToMember(memberDTO));

  }

  //상품 즐겨찾기 추가
  @Override
  public void addFavorite(String tag, Long productId) throws Exception {
    Member member = memberMapper.memberDTOToMember(getMember(tag));
    Product product = productMapper.productDTOToProduct(productService.getProduct(productId));
    OriScheMemChatFav oriScheMemChatFav = OriScheMemChatFav.builder()
        .product(product)
        .member(member)
        .build();

    oriScheMenChatFavRepository.save(oriScheMemChatFav);
  }

  //상품 즐겨찾기 해제
  @Override
  public void deleteFavorite(String tag, Long productId) throws Exception {
    Member member = memberMapper.memberDTOToMember(getMember(tag));
    Product product = productMapper.productDTOToProduct(productService.getProduct(productId));

    oriScheMenChatFavRepository.deleteByMemberAndProduct(member,product);
  }

  //상품 즐겨찾기 목록 조회
  @Override
  public List<OriScheMemChatFavDTO> getFavoriteList(String tag, SearchDTO searchDTO) throws Exception {
    Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize());
    return oriScheMenChatFavRepository.findProductsByMemberTag(tag, pageable)
        .map(oriScheMemChatFavMapper::oriScheMemChatFavToOriScheMemChatFavDTO)
        .toList();
  }

  //신고
  @Override
  public void report(ReportDTO reportDTO) throws Exception {
    log.info("ReportDTO는 {}",reportDTO);
    reportRepository.save(reportMapper.reportDTOToReport(reportDTO));
  }

  //친화력 툴팁
  @Override
  public String getAffinityToolTip() throws Exception {
    return affinity;
  }

  //나드리페이 입금
  //nadeuliPayCharge
  @Override
  public void handleNadeuliPayBalance(String tag, NadeuliPayHistoryDTO nadeuliPayHistoryDTO, NadeuliDeliveryDTO nadeuliDeliveryDTO) throws Exception {
    log.info("전달받은 tag와 nadeuliPayHistoryDTO와 nadeuliDeliveryDTO는 {},{},{}", tag, nadeuliPayHistoryDTO, nadeuliDeliveryDTO);
    //1. 전달받은 tag로 현재 멤버 잔액 조회
    MemberDTO memberDTO = getMember(tag);
    log.info("조회한 memberDTO는 {}", memberDTO);

    //2. 멤버 잔액 변수에 저장
    Long memberNadeuliPayBalance = memberDTO.getNadeuliPayBalance();

    //3. 나드리페이거래내역DTO가 null이아닐떄 거래타입을 가져옴
    TradeType tradeType = (nadeuliPayHistoryDTO != null) ? nadeuliPayHistoryDTO.getTradeType() : null;
    //4. 나드리부름DTO가 null이아닐떄 부름상태를 가져옴
    DeliveryState deliveryState = (nadeuliDeliveryDTO != null) ? nadeuliDeliveryDTO.getDeliveryState() : null;

    //5. 나드리페이 입금이 참이면 현재잔액에 +
    if (depositNadeuliPayBalance(tradeType, deliveryState)) {
      Long tradingMoney = getHandleMoney(nadeuliPayHistoryDTO, nadeuliDeliveryDTO);
      memberDTO.setNadeuliPayBalance(memberNadeuliPayBalance + tradingMoney);
    //6. 나드리페이 출금이 참이면 현재잔액에 -
    } else if (withdrawNadeuliPayBalance(tradeType, deliveryState)) {
      Long tradingMoney = getHandleMoney(nadeuliPayHistoryDTO, nadeuliDeliveryDTO);
      Long result = memberNadeuliPayBalance - tradingMoney;

      // 잔액 부족 예외 처리
      if (result < 0) {
        throw new Exception("잔액이 부족합니다.");
      }
      //7. 나드리페이 계산결과를 잔액에 set
      memberDTO.setNadeuliPayBalance(result);
    }

    //8. 멤버 잔액 업데이트
    updateMember(memberDTO);
  }


  // DTO별로 null체크해서 계산할 금액 가져오기
  private Long getHandleMoney(NadeuliPayHistoryDTO nadeuliPayHistoryDTO, NadeuliDeliveryDTO nadeuliDeliveryDTO) {
    if (nadeuliPayHistoryDTO != null) {
      return nadeuliPayHistoryDTO.getTradingMoney();
    } else if (nadeuliDeliveryDTO != null) {
      return nadeuliDeliveryDTO.getDeposit();
    }
    return 0L;
  }

  //나드리페이 입금일 때 참
  private boolean depositNadeuliPayBalance(TradeType tradeType, DeliveryState deliveryState) {
    return tradeType == TradeType.CHARGE ||
        (deliveryState != null && (deliveryState == DeliveryState.CANCEL_ORDER ||
            deliveryState == DeliveryState.CANCEL_DELIVERY ||
            deliveryState == DeliveryState.COMPLETE_DELIVERY));
  }

  //나드리페이 출금일 때 참
  private boolean withdrawNadeuliPayBalance(TradeType tradeType, DeliveryState deliveryState) {
    return (tradeType == TradeType.PAYMENT || tradeType == TradeType.WITHDRAW) ||
        (deliveryState != null && deliveryState == DeliveryState.DELIVERY_ORDER);
  }

}

