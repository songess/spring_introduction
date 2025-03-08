package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너가 뜰 때 MemberController를 생성하고 관리 해줌
public class MemberController {
    private final MemberService memberService;

    // MemberController를 생성할 때 호출
    @Autowired // spring이 memberService를 스프링 컨테이너에 있는 memberService를 가져다 연결시켜줌(의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // localhost::8080/members/new
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // Post 방식으로 localhost::8080/members/new
    public String create(MemberForm form) {
        Member member = new Member(); // member 객체 생성
        member.setName(form.getName()); // form에서 입력받은 이름을 member 객체 이름으로 넣음

        memberService.join(member); // member 객체로 join(회원가입)

        return "redirect:/"; // 바로 "localhost::8080/" 화면으로 이동
    }

    @GetMapping("/members") // localhost::8080/members
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 회원 List 가져옴

        model.addAttribute("members",members); // 회원 List를 model에 넣음
        return "members/memberList";
    }
}