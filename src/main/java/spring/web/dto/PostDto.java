package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.post.PostEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
public class PostDto {

    private Long id; // 게시물 번호, PK
    private String title; // 게시물 제목
    private String contents; // 게시물 내용
    private int count; // 게시물 조회수
    private LocalDateTime createDate; // 게시물 생성일

    // 생성자
    @Builder
    public PostDto(Long id, String title, String contents, int count, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.count = count;
        this.createDate = createDate;
    }

    // DTO --> Entity 보내는 메소드
    public PostEntity toEntity() {

        return PostEntity.builder()
                .title(title)
                .contents(contents)
                .count(count).build();

    }

}
