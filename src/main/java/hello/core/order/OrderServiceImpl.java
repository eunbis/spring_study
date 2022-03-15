package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // OrderService는 2가지가 필요함 - 회원을 찾아야 하므로 MemberRepository + 할인 정책 찾아야 하므로 DiscountPolicy
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //MemoryMember
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 유동 할인 정책
    /* 이렇게 FixDiscountPolicy에서 RateDiscountPolicy로 코드를 바꾸면 되므로, OCP, DIP 등이 잘 지켜진 것처럼 보인다.
    그러나 살펴보면, OrderServiceImpl은 DiscountPolicy 인터페이스도 의존하면서 해당 인터페이스의 구현체인 Fix-, Rate-도 의존한다. (DIP 위반)
    즉, 우리는 할인 정책 구현체를 바꾸고 싶다면 OrderServiceImpl의 코드를 변경하게 된다. (OCP 위반)
    그러므로! DIP를 위반하지 않도록 (DiscountPolicy 인터페이스만 의존하고, 구현체는 의존하지 않도록) 설정하여 자동으로 OCP도 위반하지 않도록 해야한다.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // DIP를 위반하지 않도록 설정, final을 붙이면 무조건 값을 할당해야 하므로, final을 지운다.

    @Autowired //생성자 위에 입력, 생성시 자동으로 의존성 주입하도록
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //철저하게 DIP를 지키고 있음

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //1. 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        //2. 할인 여부 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //Order 서비스는 할인 정책을 아예 모르므로, discountPolicy에 넘겨버림 (단일 책임의 원칙 설계 good)
        //DIP 위반하지 않도록 인터페이스에만 의존하도록 하면, 아무것도 할당되어있지 않으므로 이 코드를 실행했을 때 NullPointException이 발생함

        //주문 정보 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
