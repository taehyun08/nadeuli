package kr.nadeuli.mapper;

import kr.nadeuli.dto.MemberDTO;
import kr.nadeuli.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  @Mapping(target = "blocks", ignore = true)
  @Mapping(target = "bankAccounts", ignore = true)
  @Mapping(target = "reports", ignore = true)
  @Mapping(target = "oriScheMemChatFavs", ignore = true)
  @Mapping(target = "sellerProducts", ignore = true)
  @Mapping(target = "buyerProducts", ignore = true)
  @Mapping(target = "tradeReviews", ignore = true)
  @Mapping(target = "nadeuliPayHistories", ignore = true)
  @Mapping(target = "posts", ignore = true)
  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "nadeuliDeliveries", ignore = true)
  @Mapping(target = "nadeulibuyers", ignore = true)
  Member memberDTOToMember(MemberDTO memberDTO);

  MemberDTO memberToMemberDTO(Member member);

}