package spring.domain.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ReplyEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL일 경우 자동번호 부여
    Long id;

    @Column
    Long postid;

    @Column
    String replycontents;

    @Builder
    public ReplyEntity(Long id, Long postid, String replycontents) {
        this.id = id;
        this.postid = postid;
        this.replycontents = replycontents;
    }

}
