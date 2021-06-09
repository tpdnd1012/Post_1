package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.reply.ReplyEntity;
import spring.domain.reply.ReplyRepository;
import spring.web.dto.ReplyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    // 리플 생성
    public void save(ReplyDto replyDto) {

        replyRepository.save(replyDto.toEntity());

    }

    // 리플 출력
    public List<ReplyDto> replyDtoList(Long postid) {

        List<ReplyEntity> replyEntities = replyRepository.findAllBypostid(postid);

        List<ReplyDto> replyDtos = new ArrayList<>();

        for(ReplyEntity temp : replyEntities) {

            // Entity ---> DTO
            ReplyDto replyDto = ReplyDto.builder()
                    .id(temp.getId())
                    .postid(temp.getPostid())
                    .replycontents(temp.getReplycontents()).build();

            // DTO에 리스트 담기
            replyDtos.add(replyDto);

        }
        return replyDtos;
    }

    // 리플 삭제
    public void delete(Long id) {

        // 1. 엔티티 찾기
        Optional<ReplyEntity> optionalReplyEntity = replyRepository.findById(id);

        // 2. 엔티티 가져오기
        ReplyEntity replyEntity = optionalReplyEntity.get();

        // 3. 삭제처리
        replyRepository.delete(replyEntity);

    }

}
