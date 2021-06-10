package spring.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
                                                    // 테이블명, 테이블 기본키 자료형

    // 페이징처리x 검색만 작성했을 경우
    
    /*// 타이틀 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.title Like %:search%")
    List<PostEntity> findAlltitle(String search);

    // 내용 검색                // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.contents Like %:search%")
    List<PostEntity> findAllcontents(String search);

    // 작성자 검색               // 엔티티의 클래스명
    *//*@Query(value = "select p from PostEntity p where p.name Like %:search%")
    List<PostEntity> findAllname(String search);*//*

    // 번호 검색                 // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.id = :search")
    List<PostEntity> findAllid(Long search);*/

    
    // 페이징처리, 검색

    // 타이틀 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.title Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.title Like %:search%")
    Page<PostEntity> findAlltitle(Pageable pageable, String search);

    // 내용 검색                // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.contents Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.contents Like %:search%")
    Page<PostEntity> findAllcontents(Pageable pageable, String search);

    // 작성자 검색               // 엔티티의 클래스명
    /*@Query(value = "select p from PostEntity p where p.name Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.name Like %:search%)
    List<PostEntity> findAllname(Pageable pageable, String search);*/

    // 번호 검색                 // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.id = :search",
            countQuery = "select count(p.id) from PostEntity p where p.id = :search")
    Page<PostEntity> findAllid(Pageable pageable, Long search);

}
