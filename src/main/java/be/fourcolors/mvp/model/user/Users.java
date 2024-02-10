package be.fourcolors.mvp.model.user;

import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;

import java.util.HashMap;
import java.util.Map;

public class Users{
    private final Map<String, User> users;

    public Users() {
        users = new HashMap<>();
    }

    public void addUser(User user) throws IllegalUsernameException {
        if (!hasUser(user)) {
            users.put(user.getName(), user);
        }
    }

    public boolean hasUser(User user) {
        return users.containsKey(user.getName());
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
