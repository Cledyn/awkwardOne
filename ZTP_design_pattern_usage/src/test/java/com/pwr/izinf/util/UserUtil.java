package com.pwr.izinf.util;

import com.pwr.izinf.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final int MAX_ANIMAL_NUM_3 = 3;

    private UserUtil() {
    }

    public static User createUser() {
       return new User(ID, PASSWORD,MAX_ANIMAL_NUM_3);
    }

    public static List<User> createUserList(int howMany) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userList.add(new User(ID + "#" + i, PASSWORD,MAX_ANIMAL_NUM_3));
        }
        return userList;
    }

}
