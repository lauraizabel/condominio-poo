package enums;

public enum Screens {
    MAIN("mainScene"),
    LOGIN("loginScene"),
    REGISTER("registerScene");

    public String screen;

    Screens(String screen) {
        this.screen = screen;
    }
}
