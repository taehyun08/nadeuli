package kr.nadeuli.service.sms;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SmsRepository {

  /* 인증번호와 관련된 정보들을 저장, 삭제, 조회하기 위해 Repository 생성
   * Redis에 저장하는 부분에서 .set을 쓰는데 중복 방지가 자동으로 된다.
   * 마지막에 발급된 인증번호 하나만 저장된다.
   * 키 중복 불가 -> 즉 하나의 번호에는 하나의 인증 번호 정보만 가지고 있게 된다.
   * 따라서 사용자가 인증번호 발급을 여러 번 눌러 여러 개 발급 받더라도 마지막에 발급된 인증 번호로만 인증 가능하다.
   */

  private final String PREFIX = "authNum:"; // key값이 중복되지 않도록 상수 선언
  private final int LIMIT_TIME = 5 * 60; // 인증번호 유효 시간

  private final StringRedisTemplate stringRedisTemplate;

  // Redis에 저장
  public void addAuthNum(String cellphone, String authNum) {
    System.out.println(authNum);
    System.out.println(cellphone);
    stringRedisTemplate.opsForValue()
        .set(PREFIX + cellphone, authNum, Duration.ofSeconds(LIMIT_TIME));
  }

  // 휴대전화 번호에 해당하는 인증번호 불러오기
  public String getAuthNum(String cellphone) {
    return stringRedisTemplate.opsForValue().get(PREFIX + cellphone);
  }

  // 인증 완료 시, 인증번호 Redis에서 삭제
  public void deleteAuthNum(String cellphone) {
    stringRedisTemplate.delete(PREFIX + cellphone);
  }

  // Redis에 해당 휴대번호로 저장된 인증번호가 존재하는지 확인
  public boolean isAuthNum(String cellphone) {
    return Boolean.TRUE.equals(stringRedisTemplate.hasKey(PREFIX + cellphone));
  }
}