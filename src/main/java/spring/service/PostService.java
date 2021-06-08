package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.post.PostEntity;
import spring.domain.post.PostRepository;
import spring.web.dto.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시물 저장
    public void save(PostDto postDto) {

        postRepository.save(postDto.toEntity());

    }

    // 게시물 출력
    public List<PostDto> list() {

        // 모든 Entity 반환
        List<PostEntity> postEntities = postRepository.findAll();

        // 모든 Entity --> 모든 Dto
        List<PostDto> postDtos = new ArrayList<>();

        for(PostEntity temp : postEntities) {

            PostDto postDto = PostDto.builder()
                    .id(temp.getId())
                    .title(temp.getTitle())
                    .contents(temp.getContents())
                    .count(temp.getCount())
                    .createDate(temp.getCreateDate()).build();

            postDtos.add(postDto);

        }
        return postDtos;
    }

    // 게시물 개별 출력
    public PostDto postget(Long id) {

        // 해당 ID의 Entity 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        // 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .contents(postEntity.getContents())
                .createDate(postEntity.getCreateDate())
                .count(postEntity.getCount()).build();

    }

}
