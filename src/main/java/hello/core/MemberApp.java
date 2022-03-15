package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig  = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // 1. MemberService memberService = new MemberServiceImpl();

        //AppConfig의 환경설정 정보를 가지고 반환된 객체를 스프링 컨테이너에 넣어서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //  찾으려는 메소드(객체) 이름 + 타입(MemberService) 입력해주기

        Member member = new Member(1L, "memberA", Grade.VIP);
        // cmd + option + V
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }

}
