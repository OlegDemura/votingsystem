package ru.votingsystem.util;

import ru.votingsystem.model.Role;
import ru.votingsystem.model.User;
import ru.votingsystem.to.UserTo;

public class UserUtil {
    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.ROLE_USER);
    }
}
