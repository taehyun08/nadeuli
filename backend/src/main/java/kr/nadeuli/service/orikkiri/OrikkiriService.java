package kr.nadeuli.service.orikkiri;

import kr.nadeuli.dto.OriScheMemChatFavDTO;
import kr.nadeuli.dto.OrikkiriScheduleDTO;
import kr.nadeuli.dto.SearchDTO;

import java.util.List;

public interface OrikkiriService {

    void addOrikkrirSignUp(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception; // 우리끼리에 가

//    void deleteOrikkrir(long orikkiriId) throws Exception; // 우리끼리 삭제

    void deleteOrikkiriMember(String tag, long oriScheMemChatFavId) throws Exception; // 우리끼리에서 나가기

    List<OriScheMemChatFavDTO> getOrikkiriMemberList(long orikkiriId, SearchDTO searchDTO) throws Exception; // 우리끼리에 가입한 멤버 확인

    List<OrikkiriScheduleDTO> getOrikkiriScheduleList(SearchDTO searchDTO) throws Exception; // 우리끼리 스케줄 리스트

    OrikkiriScheduleDTO getOrikkiriSchedule(long orikkiriScheduleId) throws Exception; // 우리끼리 스케쥴 상세 정보

    void addOrikkiriScheduleMember(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception; // 우리끼리 스케줄 참가

    List<OriScheMemChatFavDTO> getOrikkiriScheduleMemberList(long orikkiriScheduleId, SearchDTO searchDTO) throws Exception; // 우리끼리 스케줄 참가 멤버 확인
}
