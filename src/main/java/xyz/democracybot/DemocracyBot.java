package xyz.democracybot;

import net.dv8tion.jda.api.entities.User;
import xyz.democracybot.data.DiscordServer;
import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.manager.MessageManager;
import xyz.democracybot.storage.FileManager;

import java.util.LinkedList;
import java.util.List;

public class DemocracyBot {

    private static final DemocracyBot democracyBot = new DemocracyBot();

    private MessageManager messageManager;
    private FileManager fileManager;

    private final List<DiscordServer> servers = new LinkedList<>();
    private final List<DiscordUser> users = new LinkedList<>();

    public static DemocracyBot getInstance(){
        return democracyBot;
    }
    private DemocracyBot(){
        fileManager = new FileManager(this);
messageManager =  new MessageManager(this);
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public List<DiscordServer> getServers() {
        return servers;
    }

    public List<DiscordUser> getUsers() {
        return users;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public DiscordUser getUser(User user) {
        for(DiscordUser discordUser : getUsers()){
            if(discordUser.getAccountID()==user.getIdLong()){
                return discordUser;
            }
        }
        return DiscordUser.getAccount(user);
    }
}
