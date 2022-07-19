package xyz.democracybot.manager;

public class EmojiManager {

    public static final String RED = "\uD83D\uDFE5";
    public static final String ORANGE = "\uD83D\uDFE7";
    public static final String YELLOW = "\uD83D\uDFE8";
    public static final String GREEN = "\uD83D\uDFE9";
    public static final String BLUE = "\uD83D\uDFE6";
    public static final String PURPLE = "\uD83D\uDFEA";
    public static final String BROWN = "\uD83D\uDFEB";

    public static String get(String key) {
        switch (key.toLowerCase()) {
            case "red":
                return RED;
            case "orange":
                return ORANGE;
            case "yellow":
                return YELLOW;
            case "green":
                return GREEN;
            case "blue":
                return BLUE;
            case "purple":
                return PURPLE;
            case "brown":
                return BROWN;
        }
        return key;
    }
}
