package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.reply.ReplyEntity;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {

    private Long id;
    private Long postid;
    private String replycontents;

    @Builder
    public ReplyDto(Long id, Long postid, String replycontents) {
        this.id = id;
        this.postid = postid;
        this.replycontents = replycontents;
    }

    // Dto ---> Entity : DTO는 DB로 접근불가, Entity로 변경 후 접근가능
    public ReplyEntity toEntity() {

        return ReplyEntity.builder()
                .id(id)
                .postid(postid)
                .replycontents(replycontents).build();

    }

}
