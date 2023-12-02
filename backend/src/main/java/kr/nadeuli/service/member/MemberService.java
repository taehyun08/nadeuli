package kr.nadeuli.service.member;

import java.util.List;
import kr.nadeuli.dto.BlockDTO;
import kr.nadeuli.dto.GpsDTO;
import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.dto.SearchDTO;


public interface MemberService {

  public String addTag() throws Exception;

  public void updateMember(MemberDTO memberDTO) throws Exception;

  public MemberDTO getMember(String tag) throws Exception;

  public MemberDTO getOtherMember(String tag) throws Exception;

  public List<MemberDTO> getMemberList(SearchDTO searchDTO) throws Exception;

  public String getDongNe(GpsDTO gpsDTO) throws Exception;

  public void addDisableMember(BlockDTO blockDTO, String tag) throws Exception;

  public void deleteDisableMember(String tag) throws Exception;


}
