package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repo = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repo.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repo.save(member);
        Member result = repo.findById(member.getId()).get();
        Assertions.assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        Member result = repo.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        List<Member> result = repo.findAll();
        Assertions.assertThat(result).hasSize(2);
    }

}
