package hello.core.singleton;

public class StatefulService {

//    // 문제 발생
//    private int price; //상태를 유지하는 필드 10000 -> 20000
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제
//    }

    // 수정 코드
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        // this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
