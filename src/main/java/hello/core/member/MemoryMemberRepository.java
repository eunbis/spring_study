package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{
// DB 확정이 되지 않았기 때문에 MemoryMemberRepository로 만들었음 -> 메모리에서만 사용가능, 테스트 용도로만 사용
// MemberRepository의 구현체

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 이슈 때문에 원래 ConcurrentHashMap을 써야하지만, 간단히 하기 위해 HashMap 사용

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
