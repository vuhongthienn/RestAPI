package spring.crud.restful.dto;

import lombok.Data;
import spring.crud.restful.model.Member;

import java.util.List;

@Data
public class ListMemberDTO {
    private int totalMember;
    private List<Member> memberList;
    private int currentPage;
    private int countMember;
    private int startIndex;
}
