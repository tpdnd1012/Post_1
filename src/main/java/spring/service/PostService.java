package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.post.PostEntity;
import spring.domain.post.PostRepository;
import spring.web.dto.PostDto;

import javax.transaction.Transactional;
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

    // 모든 게시물 출력
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

    // 게시물 삭제 처리
    public void postdelete(Long id) {

        // 1. 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        
        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 삭제처리
        postRepository.delete(postEntity);

    }

    // 게시물 수정 처리, @Transactional JAP 기준 꼭 수정이나 삭제 꼭 넣어줘야함
    @Transactional // 정상 여부에 따라 Commit 또는 Rollback, 실행부터 완료까지 묶는것
    public void postupdate(PostDto updateDto) {

        // 1. 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(updateDto.getId());

        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 엔티티 수정 처리
        postEntity.update(updateDto);

    }

    // 조회수 처리
    @Transactional
    public void countup(Long id) {

        // 1. 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 조회수 증가 메소드 호출
        postEntity.countup();

    }

}
