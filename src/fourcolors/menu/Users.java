package fourcolors.menu;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Users {
    private final Map<String, User> users;
    private final static String FILE_LOCATION = "users-save-data.cvs";

    public Users() {
        users = new HashMap<>();
        load(FILE_LOCATION);
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
            save(FILE_LOCATION);
        }
    }

    public void save(String filename) {

    }

    public void load(String filename) {

    }
}
