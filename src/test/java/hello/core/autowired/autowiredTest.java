package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class autowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    // (TestBean.class)에 의해 Spring Bean으로 자동 등록됨
    static class TestBean {

        @Autowired(required = false)
        // @Autowired(required = true) //오류 발생 - Bean이 없기 때문
        // 스프링 컨테이너와 관련 없는 클래스 집어넣음
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        // @Nullable은 호출은 되지만, null값이 들어옴
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        // Optional<>은 빈이 없다면 empty 값을 넣어줌
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}