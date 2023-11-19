package spring.crud.restful.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.crud.restful.dto.InputMemberDTO;
import spring.crud.restful.dto.ListMemberDTO;
import spring.crud.restful.dto.MemberDTO;
import spring.crud.restful.model.Member;
import spring.crud.restful.repository.MemberRepository;
import spring.crud.restful.service.MemberService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public ListMemberDTO findAll(int currentPage, int itemsPerPage) {
        int totalMember = memberRepository.countMember();
        int startIndex = (currentPage - 1) * itemsPerPage;
        List<Member> listMembers = memberRepository.findAll(itemsPerPage, startIndex);
        ListMemberDTO listMemberDTO = new ListMemberDTO();
        listMemberDTO.setCurrentPage(currentPage);
        listMemberDTO.setCountMember(listMembers.size());
        listMemberDTO.setTotalMember(totalMember);
        listMemberDTO.setStartIndex(startIndex);
        listMemberDTO.setMemberList(listMembers);
        return listMemberDTO;
    }

    @Override
    public MemberDTO updateMember(Long memberId, InputMemberDTO inputMemberDTO) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            throw new RuntimeException("Not found member need edit");
        }
        return saveInDB(member, inputMemberDTO);
    }

    @Override
    public MemberDTO createMember(InputMemberDTO inputMemberDTO) {
        Member member = new Member();
        member.setCreateAt(LocalDateTime.now());
        return saveInDB(member, inputMemberDTO);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            throw new RuntimeException("Not found member need delete");
        }
    }

    @Override
    public MemberDTO findDetail(Long memberId, boolean enable) {
        Member member = memberRepository.findByIdAndBlock(memberId,enable).orElse(null);
        if (member == null) {
            throw new RuntimeException("Not found member with enable = "+ enable);
        }
        MemberDTO memberDTO = mapper.map(memberRepository.save(member), MemberDTO.class);
        return memberDTO;
    }

    public MemberDTO saveInDB(Member member, InputMemberDTO inputMemberDTO) {
        member.setMemberName(inputMemberDTO.getMemberName());
        member.setSalary(inputMemberDTO.getSalary());
        member.setBirthday(inputMemberDTO.getBirthday());
        member.setPostCode(inputMemberDTO.getPostCode());
        member.setEmail(inputMemberDTO.getEmail());
        if (!isValidEmail(inputMemberDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        member.setGender(inputMemberDTO.getGender());
        member.setAddress(inputMemberDTO.getAddress());
        member.setBlock(inputMemberDTO.isBlock());
        MemberDTO memberDTO = mapper.map(memberRepository.save(member), MemberDTO.class);
        return memberDTO;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
