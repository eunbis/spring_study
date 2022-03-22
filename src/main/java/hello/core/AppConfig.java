package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration 붙이지 않아도 스프링 컨테이너에 Bean으로 등록되지만, CGLIB 기술이 적용되지 않음 -> 싱글톤 깨짐
@Configuration
public class AppConfig { //Application 전체를 설정하고 구성(어떻게 동작할지) - 애플리케이션의 실제 동작에 필요한 구현 객체를 생성하고, 생성한 객체 인스턴스의 참조를 생성자 주입을 통해 연결하는 역할을 함

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //실행순서 (사실 순서는 보장되지 않음)
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실제 호출
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    // AppConfig.memberService()를 하게 되면 MemberServiceImpl(구현객체)이 생성되며 MemoryMemberRepository 인스턴스가 생성자로 넘어가 MemberServiceImpl의 구현체가 된다.
    @Bean //  이게 붙은 메소드를 호출한 후 반환된 객체를 Spring 컨테이너에 등록됨
   public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        // return new MemberServiceImpl(new MemoryMemberRepository());
        // OrderServiceImpl의 인자와 중복이 존재함, 따라서 MemoryMemberRepository 구현체를 다른 구현체로 변경하고자 할 때 코드를 두 번 변경해야 했었음
        return new MemberServiceImpl(memberRepository()); //리펙터링시 returnType은 인터페이스를 선택해야 함
        //구현체 변경 시, MemberRepository 메소드의 return 부분만 변경하면 됨
    }

    //인터페이스를 반환해주는 MemberRepository 역할
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        // return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null; // 필드 주입 test시 에러 방지
    }

    // 마찬가지로 DiscountPolicy 역할도 추가해줌
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    // 메소드 명을 통해 역할이 다 잘 드러나고, 어떤 구현체를 쓰는지도 드러남 (구현체를 바꿀 경우 return 문만 바꿔주면 됨)
    // 코드의 구성 정보를 살펴보면 설계했던 것을 그대로 볼 수 있음 (역할 - 역할에 대한 구현)
    // 역할을 세우고, 그 안에 구현체가 들어가 있는 모습으로 구현 완료
}