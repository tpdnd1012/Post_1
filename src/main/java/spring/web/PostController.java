package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.domain.post.PostEntity;
import spring.service.PostService;
import spring.service.ReplyService;
import spring.web.dto.PostDto;
import spring.web.dto.ReplyDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ReplyService replyService;

    // 게시판 페이지 요청
    @GetMapping("/postlist")
    public String postlist(Model model) {

        List<PostDto> postDtos = postService.list();

        model.addAttribute("postDtos", postDtos);

        return "postlist";

    }

    // 게시판 등록 페이지 요청
    @GetMapping("/postwrite")
    public String postwrite() {

        return "postwrite";

    }

    // 게시물 등록 처리
    @PostMapping("/postwrite")
    public String postwrite_c(PostDto postDto) {

        postService.save(postDto);

        return "redirect:/postlist";

    }

    // 게시물 상세 페이지
    @RequestMapping(value = "/postview", method = RequestMethod.GET)
    public String postview(@RequestParam Long id, @RequestParam("count") int count, Model model) {
                        // @RequestParam : 경로(URL) 상에 변수 가져오기

        // 조회수 처리
        if(count != -1) postService.countup(id);

        // 해당 게시물 출력
        PostDto postDto = postService.postget(id);
        model.addAttribute("postDto", postDto);

        // 댓글 출력
        List<ReplyDto> replyDtos = replyService.replyDtoList(postDto.getId());
        model.addAttribute("replyDtos", replyDtos);

        return "postview";

    }

    // 게시물 삭제 처리
    @GetMapping("/postdelete/{id}")
    public String postdelete(@PathVariable Long id) {

        postService.postdelete(id);

        return "redirect:/postlist";

    }

    // 게시물 수정 페이지 요청
    @GetMapping("/postupdate/{id}")
    public String postupdate(@PathVariable Long id, Model model) {

        PostDto postDto = postService.postget(id);

        model.addAttribute("postDto", postDto);

        return "postupdate";

    }

    // 게시물 수정 처리
    @PostMapping("/postupdate")
    public String postupdate_c(PostDto updateDto) {

        postService.postupdate(updateDto);

        return "redirect:/postlist";

    }

}
