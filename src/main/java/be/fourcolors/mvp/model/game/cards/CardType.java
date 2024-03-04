package be.fourcolors.mvp.model.game.cards;

public enum CardType {
    DRAW("Draw"),
    REVERSE("Reverse"),
    SKIP("Skip"),
    CHANGE("Wild");

    private final String fileName;

    CardType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

