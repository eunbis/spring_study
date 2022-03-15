package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 자기 자신을 내부의 private, static으로 가지고 있음
    // 이렇게 하면 class 레벨에 올라가므로 딱 하나만 가지고 있을 수 있다.
    // 이러면 JVM이 실행될 때 SingletonService() 객체 생성해서 instance에 넣어둠

    // public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");

    }
}