package ru.votingsystem.web.user;

import org.springframework.stereotype.Controller;
import ru.votingsystem.model.User;

import java.util.List;

import static ru.votingsystem.util.ValidationUtil.checkNew;

@Controller
public class AdminRestController extends AbstractUserController {

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    public User getByMail(String email) {
        log.info("getByMail {}", email);
        return service.getByEmail(email);
    }
}
