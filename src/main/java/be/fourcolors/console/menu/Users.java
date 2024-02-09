package be.fourcolors.console.menu;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
