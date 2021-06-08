package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.domain.post.PostEntity;
import spring.service.PostService;
import spring.web.dto.PostDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
    @GetMapping("/postview/{id}")
    public String postview(@PathVariable Long id, Model model) {

        PostDto postDto = postService.postget(id);

        model.addAttribute("postDto", postDto);

        return "postview";

    }

}
