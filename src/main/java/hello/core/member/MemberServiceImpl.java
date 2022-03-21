package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")
public class MemberServiceImpl implements MemberService{
  // 구현체가 하나만 있을 때 뒤에 Impl을 붙여서 사용한다. MemberService의 구현체이다.

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //이전처럼 MemberServiceImpl이 구현체를 설정하는 것이 아닌, AppConfig에서 구현체를 지정하기 위함

    //생성자를 통해 AppConfig에서 설정한 구현체를 넣어주도록 설정 (의존관계 주입)
    @Autowired //의존관계 자동주입 ac.getBean(MemberRepository.class)의 역할을 함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // save 호출시 다형성에 의해 MemberRepository 인터페이스가 아니라,
        // MemoryMemberRepository에 있는 save (override 한 것)가 호출된다.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
