package xyz.democracybot.data;

public class Role {

    private String displayname;
    private String name;

    public Role(String name, String displayname){
        this.displayname = displayname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDisplayname() {
        return displayname;
    }
}
