package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.domain.post.PostEntity;
import spring.service.PostService;
import spring.service.ReplyService;
import spring.web.dto.PostDto;
import spring.web.dto.ReplyDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ReplyService replyService;

    // 게시판 페이지 요청 [ 검색만 작성했을 경우 ]
    /*@GetMapping("/postlist")
    public String postlist(Model model, HttpServletRequest request) {

        // form에서 키워드와 검색어 요청
        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        List<PostDto> postDtos = postService.list(keyword, search);

        model.addAttribute("postDtos", postDtos);

        return "postlist";

    }*/

    // 게시판 페이지 요청 [ 페이징처리, 검색 ]
    @GetMapping("postlist")
    public String postlist(Model model, HttpServletRequest request, @PageableDefault Pageable pageable) {

        // form에서 키워드와 검색어 요청
        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        Page<PostEntity> postEntities = postService.list(pageable, keyword, search);

        model.addAttribute("postDtos", postEntities);

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

    // 게시물 검색 [ 페이징처리x, 검색만 작성했을 경우 ]
    /*@PostMapping("/postsearch")
    public String postsearch_c(HttpServletRequest request, Model model) {
                            // DTO와 form 태그 name 동일한경우 DTO 자동주입
                            // DTO 없는 경우 request 객체 만들기

        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        // 검색이 없으면
        if(search.equals("")) {

            return "redirect:/postlist";

        }

        List<PostDto> postDto = postService.list(keyword, search);

        model.addAttribute("postDtos", postDto);

        return "postlist";

    }*/
    
    // 게시물 검색 [ 페이징처리, 검색 ]
    @PostMapping("postsearch")
    public String postsearch(Model model, HttpServletRequest request, @PageableDefault Pageable pageable) {

        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        // 검색이 없으면
        if(search.equals("")) {

            return "redirect:/postlist";

        }

        Page<PostEntity> postEntities = postService.list(pageable, keyword, search);

        model.addAttribute("postDtos", postEntities);

        return "postlist";

    }

}
