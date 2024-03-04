package be.fourcolors.mvp.model.game.cards;

public enum CardColor {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    WILD("Wild");

    private final String fileName;

    CardColor(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
