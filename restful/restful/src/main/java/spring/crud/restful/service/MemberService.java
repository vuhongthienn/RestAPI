package spring.crud.restful.service;

import spring.crud.restful.dto.InputMemberDTO;
import spring.crud.restful.dto.ListMemberDTO;
import spring.crud.restful.dto.MemberDTO;

public interface MemberService {

    ListMemberDTO findAll(int currentPage, int itemsPerPage);

    MemberDTO updateMember(Long memberId, InputMemberDTO inputMemberDTO);

    MemberDTO createMember(InputMemberDTO inputMemberDTO);

    void deleteMember(Long memberId);

    MemberDTO findDetail(Long memberId, boolean enable);
}
