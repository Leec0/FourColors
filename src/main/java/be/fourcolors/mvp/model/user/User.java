package be.fourcolors.mvp.model.user;


import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;
import javafx.scene.paint.Color;

public class User {
    private final String name;
    private BackgroundColor favoriteColor;
    private int wins;

    public User(String name) throws IllegalUsernameException{
        if (name.isEmpty()) throw new IllegalUsernameException("Username mag niet leeg zijn!");
        this.name = name;
        this.favoriteColor = BackgroundColor.WHITE;
        this.wins = 0;
    }

    public User(String name, BackgroundColor favoriteColor) throws IllegalUsernameException{
        if (name.isEmpty()) throw new IllegalUsernameException("Username mag niet leeg zijn!");
        if (favoriteColor == null) throw new IllegalUsernameException("Er moet een kleur gekozen worden!");
        this.name = name;
        this.favoriteColor = favoriteColor;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public BackgroundColor getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(BackgroundColor favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }

    public String toString() {
        return String.format("\"%s\",\"%s\",%d", name, favoriteColor.name(), wins);
    }
}
