package ru.votingsystem.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.votingsystem.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String getAll(Model model, HttpServletRequest request) {
        List<Meal> list = super.getAll(getId(request, "restaurantId"));
        model.addAttribute("meals", list);
        return "meals";
    }

    @GetMapping("/update")
    public String update(Model model, HttpServletRequest request) {
        model.addAttribute("meal", super.get(getId(request, "mealId"), getId(request, "restaurantId")));
        return "mealForm";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request, "mealId"), getId(request, "restaurantId"));
        return "redirect:/meals";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Meal meal = new Meal("", 1000, LocalDate.now());
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Meal meal = new Meal(request.getParameter("description"),
                Integer.valueOf(request.getParameter("price")),
                LocalDate.parse(request.getParameter("dateTime")));
        if (request.getParameter("id").isEmpty()) {
            super.create(meal, getId(request, "restaurantId"));
        } else {
            super.update(meal, getId(request, "restaurantId"), getId(request, "id"));
        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
