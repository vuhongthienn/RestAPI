package spring.crud.restful.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.crud.restful.dto.InputMemberDTO;
import spring.crud.restful.dto.ListMemberDTO;
import spring.crud.restful.dto.MemberDTO;
import spring.crud.restful.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("members")
    public ResponseEntity<ListMemberDTO> readAllMember(@RequestParam Optional<Integer> currentPage,
                                                                         @RequestParam Optional<Integer> itemsPerPage){
        ListMemberDTO listMemberDTO = memberService.findAll(currentPage.orElse(1),itemsPerPage.orElse(20));
        return ResponseEntity.ok(listMemberDTO);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberDTO> readDetailMember(@PathVariable Long memberId, @RequestParam("enable") boolean enable){
        MemberDTO listMemberDTO = memberService.findDetail(memberId, enable);
        return ResponseEntity.ok(listMemberDTO);
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable Long memberId, @RequestBody InputMemberDTO inputMemberDTO) {
        MemberDTO memberDTO = memberService.updateMember(memberId, inputMemberDTO);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<?> createMember(@RequestBody InputMemberDTO inputMemberDTO) {
        MemberDTO memberDTO = memberService.createMember(inputMemberDTO);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
         memberService.deleteMember(memberId);
        return new ResponseEntity<>("Success delete member!", HttpStatus.OK);
    }
}
