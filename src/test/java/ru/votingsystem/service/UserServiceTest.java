package ru.votingsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.votingsystem.model.Role;
import ru.votingsystem.model.User;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void get() {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER)));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void delete() {
        service.delete(ADMIN_ID);
        assertMatch(service.getAll(), USER);
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    public void create() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() {
        User userUpdate = new User(USER);
        userUpdate.setName("NewName");
        service.update(userUpdate);
        assertMatch(service.get(USER_ID), userUpdate);
    }
}