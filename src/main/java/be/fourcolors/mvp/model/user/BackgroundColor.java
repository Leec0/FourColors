package be.fourcolors.mvp.model.user;

public enum BackgroundColor {
    BLACK("backgroundBlack.jpg", "Zwart"),
    BLUE("backgroundBlue.jpg", "Blauw"),
    GRAY("backgroundGray.jpg", "Grijs"),
    GREEN("backgroundGreen.jpg", "Groen"),
    LIGHT_BLUE("backgroundLightBlue.jpg", "Licht blauw"),
    PURPLE("backgroundPurple.jpg", "Paars"),
    RED("backgroundRed.jpg", "Rood"),
    WHITE("backgroundWhite.jpg", "Wit");
    private final String backgroundFile;
    private final String readableName;

    BackgroundColor(String backgroundFile, String readableName) {
        this.backgroundFile = backgroundFile;
        this.readableName = readableName;
    }

    public String getBackgroundFile() {
        return backgroundFile;
    }

    public String getReadableName() {
        return readableName;
    }
}
