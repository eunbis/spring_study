package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
    //member를 알아야 할인 정책을 적용할 수 있기 때문임
}
