package spring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class) // 해당 클래스 엔티티 자동 주입
@MappedSuperclass // 해당 클래스를 슈퍼클래스로
public class BaseTime { // DB에 생성시간, 수정시간 자동주입 클래스

    @CreatedDate
    @Column(updatable = false) // 변경 불가
    private LocalDateTime createDate; // 생성시간

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 수정시간

}
