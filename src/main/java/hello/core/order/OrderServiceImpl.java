package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 붙은 필드를 가지고 생성자를 자동으로 생성
public class OrderServiceImpl implements OrderService{

    // 생성자 주입 사용시 final 키워드를 넣어 오류 방지 가능
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // DIP를 위반하지 않도록 설정, final을 붙이면 무조건 값을 할당해야 하므로, final을 지운다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //1. 회원 정보 조회
        Member member = memberRepository.findById(memberId); //NullPointerException 발생
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
