package xyz.democracybot;

import xyz.democracybot.data.DiscordServer;
import xyz.democracybot.data.DiscordUserAcc;
import xyz.democracybot.storage.TextManager;

import java.util.LinkedList;
import java.util.List;

public class DemocracyBot {

    private static final DemocracyBot democracyBot = new DemocracyBot();

    private final TextManager textManager = new TextManager();

    private final List<DiscordServer> servers = new LinkedList<>();
    private final List<DiscordUserAcc> users = new LinkedList<>();

    public static DemocracyBot getInstance(){
        return democracyBot;
    }
    private DemocracyBot(){

    }

    public List<DiscordServer> getServers() {
        return servers;
    }

    public List<DiscordUserAcc> getUsers() {
        return users;
    }

    public TextManager getTextManager() {
        return textManager;
    }
}
