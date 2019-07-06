package ru.votingsystem.web;

import ru.votingsystem.model.AbstractBaseEntity;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthId(int id) {
        SecurityUtil.id = id;
    }
}
