package xyz.democracybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import xyz.democracybot.command.VoteCreateCommand;
import xyz.democracybot.data.DiscordServer;
import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;
import xyz.democracybot.manager.CommandManager;
import xyz.democracybot.manager.MessageManager;
import xyz.democracybot.manager.ReactionActionManager;
import xyz.democracybot.manager.VoteManager;
import xyz.democracybot.storage.FileManager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DemocracyBot {

    private static final DemocracyBot democracyBot = new DemocracyBot();

    private MessageManager messageManager;
    private FileManager fileManager;
    private VoteManager voteManager;
    private CommandManager commandManager;
    private ReactionActionManager reactionActionManager;

    private List<Message> messages  = new LinkedList<>();
    private final List<DiscordServer> servers = new LinkedList<>();
    private final List<DiscordUser> users = new LinkedList<>();

    private JDA jda;

    public static DemocracyBot getInstance() {
        return democracyBot;
    }

    private DemocracyBot() {
        fileManager = new FileManager(this);
        messageManager = new MessageManager(this);
        this.voteManager = new VoteManager(this);
        this.commandManager = new CommandManager(this);
        this.reactionActionManager = new ReactionActionManager();


        this.commandManager.registerCommandExecutor("cvote",new VoteCreateCommand());

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

    public VoteManager getVoteManager() {
        return voteManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public DiscordUser getUser(User user) {
        for (DiscordUser discordUser : getUsers()) {
            if (discordUser.getAccountID().equals(user.getId())) {
                return discordUser;
            }
        }
        return DiscordUser.getAccount(user);
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public JDA getJda() {
        return jda;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public Message getMessage(long id){
        for(Message message: messages){
            if(message.getId().isFullfilled()&&message.getId().get()==id){
                return message;
            }
        }


        File file = getFileManager().getMessage(id);
        if(file.exists()){
            Message message = new Message(id,file);
            messages.add(message);
            return message;
        }else{
            Message message = new Message(id,file);
            messages.add(message);
            return message;
        }
    }

    public ReactionActionManager getReactionActionManager() {
        return reactionActionManager;
    }
}
