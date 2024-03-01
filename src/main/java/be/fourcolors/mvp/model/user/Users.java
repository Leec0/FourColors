package be.fourcolors.mvp.model.user;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Users{
    private final static String SAVE_FILE = "src/main/resources/save.csv";
    private final Map<String, User> users;

    public Users() {
        users = new HashMap<>();
        load(SAVE_FILE);
    }

    public void addUser(User user) throws IllegalArgumentException, IOException {
        if (!hasUser(user)) {
            users.put(user.getName().toLowerCase(), user);
            save(SAVE_FILE);
        }
    }

    public boolean hasUser(User user) {
        return users.containsKey(user.getName().toLowerCase());
    }

    public Map<String, User> getUsers() {
        return users;
    }

    private void save(String fileName) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            if (new File(fileName).length() == 0) {
                printWriter.println("\"name\",\"color\",\"wins\"");
            }
            for (User user : users.values()) {
                printWriter.println(user);
            }
        }
    }

    private void load(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))){
            while (scanner.hasNextLine()) {
                String userData = scanner.nextLine();
                if (!userData.equals("\"name\",\"color\",\"wins\"")) {
                    String[] data = userData.split(",");
                    String name = data[0];
                    BackgroundColor backgroundColor = BackgroundColor.valueOf(data[1]);
                    int wins = Integer.parseInt(data[2]);
                    User user = new User(name, backgroundColor, wins);
                    users.put(name.toLowerCase(), user);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
