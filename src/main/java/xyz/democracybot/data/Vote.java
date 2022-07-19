package xyz.democracybot.data;

public class Vote {

    private int[] optionsScore;
    private String[] optionDesc;
    private int totalScoreCount = 0;
    private long id;
    private String name;
    private String description;
    private long time;

    public Vote(String name, String description, long id, int[] optionsScore, String[] optinonDescriptions, long time){
        this.optionsScore = optionsScore;
        this.id =id;
        this.name = name;
        this.description=description;
        this.optionDesc = optinonDescriptions;
        this.time = time;
    }
    public Vote(String name, String description, long id, String[] optinonDescriptions, long time){
        this.optionsScore = new int[optinonDescriptions.length];
        this.id =id;
        this.name = name;
        this.description=description;
        this.optionDesc = optinonDescriptions;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getOptionDesc() {
        return optionDesc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getOptionsScore() {
        return optionsScore;
    }

    public int getTotalScoreCount() {
        return totalScoreCount;
    }

    public long getId() {
        return id;
    }

    public void setTotalScoreCount(int totalScoreCouint) {
        this.totalScoreCount = totalScoreCouint;
    }
}
