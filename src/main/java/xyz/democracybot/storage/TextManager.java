package xyz.democracybot.storage;

public class TextManager {

    public static final String DEFAULT_WELCOME_DM = "Welcome! This is a test message.";
    public static final String DEFAULT_PRONOUNS_DM = "Please select your pronouns here by reacting to this message:";

    private String welcomeDm = DEFAULT_WELCOME_DM;
    private String pronounsDM = DEFAULT_PRONOUNS_DM;

    public String getPronounsDM() {
        return pronounsDM;
    }

    public void setPronounsDM(String pronounsDM) {
        this.pronounsDM = pronounsDM;
    }

    public String getWelcomeDm() {
        return welcomeDm;
    }

    public void setWelcomeDm(String welcomeDm) {
        this.welcomeDm = welcomeDm;
    }
}
