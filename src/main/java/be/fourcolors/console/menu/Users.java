package be.fourcolors.console.menu;

import java.util.HashMap;
import java.util.Map;

public class Users{
    private final Map<String, User> users;

    public Users() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
        }
    }
}
