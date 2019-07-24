package ru.votingsystem.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.votingsystem.model.Meal;
import ru.votingsystem.model.Restaurant;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @GetMapping
    public String getAll(Model model) {
        List<Restaurant> list = super.getAll();
        model.addAttribute("restaurants", list);
        return "restaurants";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request, "restaurantId"));
        return "redirect:/restaurant";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Restaurant restaurant = new Restaurant("", "");
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(Model model, HttpServletRequest request) {
        model.addAttribute("restaurant", super.get(getId(request, "restaurantId")));
        return "restaurantForm";
    }

    @GetMapping("/view")
    public String view(Model model, HttpServletRequest request) {
        Restaurant restaurant = super.getWithMeals(getId(request, "restaurantId"));
        List<Meal> list = restaurant.getMeals();
        model.addAttribute("restaurant", super.get(getId(request, "restaurantId")));
        model.addAttribute("meals", list);
        return "meals";
    }

    @PostMapping
    public String save(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("name"),
                request.getParameter("address"));
        if (request.getParameter("restaurantId").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getId(request, "restaurantId"));
        }
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
