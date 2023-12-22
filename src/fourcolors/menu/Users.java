package fourcolors.menu;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
            try {
                save(FILE_LOCATION);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void save(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            if (new File(filename).length() == 0) {
                writer.println("\"name\",\"color\",\"wins\"");
            }
            for (User user : users.values()) {
                writer.println(user);
            }
        }
    }

    public void load(String filename) {
        try {
            try (Scanner sc = new Scanner(new File(filename))) {
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String[] data = sc.next().replace("\"", "").split(",");
                    String name = data[0];
                    Color color = new Color(Integer.parseInt(data[1]));
                    int wins = Integer.parseInt(data[2]);
                    addUser(new User(name, color, wins));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
