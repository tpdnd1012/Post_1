package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.service.ReplyService;
import spring.web.dto.ReplyDto;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @RequestMapping("/replywrite")
    public String replywrite(ReplyDto replyDto, RedirectAttributes re) {

        replyService.save(replyDto);

        re.addAttribute("id", replyDto.getPostid());

        re.addAttribute("count", -1);

        return "redirect:/postview";

    }

    @GetMapping("/replydelete")
    public String replydelete(@RequestParam("id") Long id, @RequestParam("postid") Long postid, RedirectAttributes re) {

        replyService.delete(id);

        re.addAttribute("id", postid);

        re.addAttribute("count", -1);

        return "redirect:/postview";

    }

}
