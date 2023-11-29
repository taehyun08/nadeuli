package kr.nadeuli.mapper;


import kr.nadeuli.dto.OrikkiriDTO;
import kr.nadeuli.entity.Orikkiri;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface OrikkiriMapper {

    @Mapping(target = "ansQuestions", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "orikkiriSchedules", ignore = true)
    @Mapping(target = "oriScheMemChatFavs", ignore = true)
    Orikkiri orikkiriDTOToOrikkiri(OrikkiriDTO orikkiriDTO);

    OrikkiriDTO orikkiriToOrikkiriDTO(Orikkiri orikkiri);


}
