package ru.votingsystem.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.votingsystem.model.Vote;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/votes")
public class JspVoteController extends AbstractVoteController {

   /* @GetMapping
    public String getAll(Model model, HttpServletRequest request) {
        List<Vote> list = super.getAll(getId(request, "restaurantId"));
        model.addAttribute("votes", list);
        return "votes";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request, "id"), getId(request, "restaurantId"));
        return "redirect:/restaurants";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request) {
        super.create(new Vote(LocalDateTime.now()), getId(request, "restaurantId"));
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }*/
}
