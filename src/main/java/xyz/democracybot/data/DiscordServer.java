package xyz.democracybot.data;

public class DiscordServer {

    private final long serverid;

    public DiscordServer(long serverid){
        this.serverid = serverid;
    }

    public long getServerid() {
        return serverid;
    }
}
