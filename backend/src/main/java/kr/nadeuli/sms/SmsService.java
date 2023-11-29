package kr.nadeuli.sms;

import kr.nadeuli.dto.SmsDTO;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface SmsService {

  public SingleMessageSentResponse sendOne(String cellphone);
//  public void sendOne(String cellphone);

  public String verifySms(SmsDTO requestDto) throws Exception;


}
