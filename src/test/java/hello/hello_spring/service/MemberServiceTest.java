package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 메서드 시작되기 전
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 메서드 시작된 후
    public void afterEach() {
        memberRepository.clearStore();
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void singup() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long memberId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(memberId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplication_member_exception(){
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try{
//            memberService.join(member2);
//        }catch (Exception e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}