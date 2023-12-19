package fourcolors.menu;

import java.awt.*;

public class User {
    private final String name;
    private Color favoriteColor;
    private int wins;

    public User(String name) {
        this.name = name;
        this.favoriteColor = Color.BLUE;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public Color getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(Color favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }
}
