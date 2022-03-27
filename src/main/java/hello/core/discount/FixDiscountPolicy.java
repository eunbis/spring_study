package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
    //할인 정책의 구현체 - 정액 할인 정책
    private int discountFixAccount = 1000; //고정적으로 1000원 할인

    @Override
    public int discount(Member member, int price) {
        //회원의 등급이 VIP 일때만 할인이 들어가므로, 확인해주기
        if(member.getGrade() == Grade.VIP) {
            return discountFixAccount; //맞다면 discount에 1000원을 return
        } else {
            return 0;
        }
    }

}
