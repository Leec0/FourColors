package be.fourcolors.mvp.model.user;

public class User {
    private final String name;
    private BackgroundColor favoriteColor;
    private int wins;

    public User(String name) throws IllegalArgumentException{
        this(name, BackgroundColor.WHITE, 0);
    }

    public User(String name, BackgroundColor favoriteColor) throws IllegalArgumentException{
        this(name, favoriteColor, 0);
    }

    public User(String name, BackgroundColor favoriteColor, int wins) {
        if (name.isEmpty()) throw new IllegalArgumentException("Username mag niet leeg zijn!");
        if (favoriteColor == null) {
            favoriteColor = BackgroundColor.WHITE;
        }
        this.name = name;
        this.favoriteColor = favoriteColor;
        this.wins = wins;
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
        return String.format("%s,%s,%d", name, favoriteColor.name(), wins);
    }
}
