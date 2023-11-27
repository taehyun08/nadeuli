package kr.nadeuli.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

  private String tag;
  private String cellphone;
  private String nickname;
  private int affinity;
  private String email;
  private String dongNe;
  private String picture;
  private int nadeuliPayBalance;
  private boolean isActivate;
  private boolean isNadeuliDelivery;
  private int role;
  private String gu;
  private String bankName;
  private String bankAccountNum;
  private String blockReason;
  private LocalDateTime blockEnd;
  private Integer blockDay;

}