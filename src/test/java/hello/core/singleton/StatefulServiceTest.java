package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.nimbus.State;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        //위 스프링 컨테이너는 StatefulService Bean 하나만 생성해서 사용하는 것임
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A: A 사용자 10000원 주문 (지역변수로 선언)
        int userAPrice = statefulService1.order("userA", 10000);
        //Thread B: B 사용자 20000원 주문 (같은 객체 사용, userA의 데이터 덮어씌움)
        int userBPrice = statefulService2.order("userB", 20000);

        //Thead A: 사용자A 주문 금액 조회 - 지역변수로 변경 후에는 사용할 필요 없
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);
        // 10000원이 출력될 것을 기대했지만, 20000원이 출력되었음

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}