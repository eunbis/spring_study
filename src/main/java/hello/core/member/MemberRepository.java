package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 회원 저장

    Member findById(Long memberId); // 회원의 ID로 회원 찾기
}
