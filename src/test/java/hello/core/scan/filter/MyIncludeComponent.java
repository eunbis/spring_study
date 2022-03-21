package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // TYPE: Class 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented

//Component 스캔에서 제외
public @interface MyIncludeComponent {

}
