package spring.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BaseTime;
import spring.web.dto.PostDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor // 빈 생성자
public class PostEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL 일 경우 자동번호 부여
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private int count;

    // 생성자
    @Builder
    public PostEntity(Long id, String title, String contents, int count) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.count = count;
    }

    // 업데이트 메소드
    public void update(PostDto postDto) {

        this.title = postDto.getTitle();
        this.contents = postDto.getContents();

    }

    // 조회수 증가 메소드
    public void countup() {

        this.count++;

    }

}
