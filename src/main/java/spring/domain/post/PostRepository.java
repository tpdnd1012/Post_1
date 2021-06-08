package spring.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
                                                    // 테이블명, 테이블 기본키 자료형
}
