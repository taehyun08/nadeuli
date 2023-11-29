package kr.nadeuli.service.orikkiri;

import kr.nadeuli.dto.OriScheMemChatFavDTO;
import kr.nadeuli.dto.OrikkiriScheduleDTO;

import java.util.List;

public interface OrikkiriService {

    void addOrikkrirSignUp(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception; // 우리끼리에 가

    void deleteOrikkrir(long orikkiriId) throws Exception; // 우리끼리 삭제

    void deleteOrikkiriMember(long oriScheMemChatFavId) throws Exception; // 우리끼리에서 나가기

    List<OriScheMemChatFavDTO> getOrikkiriMemberList(long oriScheMemChatFavId) throws Exception; // 우리끼리에 가입한 멤버 확인

    List<OrikkiriScheduleDTO> getOrikkiriScheduleList(long orikkiriScheduleId) throws Exception; // 우리끼리 스케줄 리스트

    OrikkiriScheduleDTO getOrikkiriSchedule(long orikkiriScheduleId) throws Exception; // 우리끼리 스케쥴 상세 정 // 우리끼리

    void addOrikkiriScheduleMember(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception; // 우리끼리 스케줄 참가

    List<OriScheMemChatFavDTO> getOrikkiriScheduleMemberList(long oriScheMemChatFavId) throws Exception; // 우리끼리 스케줄 참가 멤버 확인
}
