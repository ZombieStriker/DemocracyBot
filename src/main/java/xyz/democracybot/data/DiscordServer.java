package xyz.democracybot.data;

public class DiscordServer {

    private final String serverid;
    private String name;

    public DiscordServer(String serverid, String name){
        this.serverid = serverid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerid() {
        return serverid;
    }
}
