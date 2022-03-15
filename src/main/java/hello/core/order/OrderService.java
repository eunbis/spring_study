package hello.core.order;

public interface OrderService {

    //return값이 Order
    Order createOrder(Long memberId, String itemName, int itemPrice);
    //  클라이언트는 회원id, 상품명, 상품 가격을 주문 서비스 역할로 넘김

}
