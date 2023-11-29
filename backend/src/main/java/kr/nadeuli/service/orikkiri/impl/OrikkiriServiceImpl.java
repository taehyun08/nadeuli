package kr.nadeuli.service.orikkiri.impl;

import kr.nadeuli.dto.OriScheMemChatFavDTO;
import kr.nadeuli.dto.OrikkiriScheduleDTO;
import kr.nadeuli.entity.OriScheMemChatFav;
import kr.nadeuli.mapper.OriScheMemChatFavMapper;
import kr.nadeuli.mapper.OrikkiriMapper;
import kr.nadeuli.service.orikkiri.OrikkiriRepository;
import kr.nadeuli.service.orikkiri.OrikkiriService;
import kr.nadeuli.service.orikkiri.OrischeMemChatFavRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service("orikkiriServiceImpl")
public class OrikkiriServiceImpl implements OrikkiriService {

    private final OrikkiriRepository orikkiriRepository;
    private final OrischeMemChatFavRepository orischeMemChatFavRepository;
    private final OriScheMemChatFavMapper oriScheMemChatFavMapper;
    private final OrikkiriMapper orikkiriMapper;


    @Override
    public void addOrikkrirSignUp(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception {
        OriScheMemChatFav oriScheMemChatFav = oriScheMemChatFavMapper.oriScheMemChatFavDTOToOriScheMemChatFav(oriScheMemChatFavDTO);
        log.info(oriScheMemChatFav);
        orischeMemChatFavRepository.save(oriScheMemChatFav);
    }

    @Override
    public void deleteOrikkrir(long orikkiriId) throws Exception {
        log.info(orikkiriId);
        orikkiriRepository.deleteById(orikkiriId);
    }

    @Override
    public void deleteOrikkiriMember(long oriScheMemChatFavId) throws Exception {
        log.info(oriScheMemChatFavId);
        orischeMemChatFavRepository.deleteById(oriScheMemChatFavId);
    }

    @Override
    public List<OriScheMemChatFavDTO> getOrikkiriMemberList(long oriScheMemChatFavId) throws Exception {
        return null;
    }

    @Override
    public List<OrikkiriScheduleDTO> getOrikkiriScheduleList(long orikkiriScheduleId) throws Exception {
        return null;
    }

    @Override
    public OrikkiriScheduleDTO getOrikkiriSchedule(long orikkiriScheduleId) throws Exception {
        return null;
    }

    @Override
    public void addOrikkiriScheduleMember(OriScheMemChatFavDTO oriScheMemChatFavDTO) throws Exception {

    }

    @Override
    public List<OriScheMemChatFavDTO> getOrikkiriScheduleMemberList(long oriScheMemChatFavId) throws Exception {
        return null;
    }


}
