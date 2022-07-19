package xyz.democracybot.data;

import net.dv8tion.jda.api.entities.User;
import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.messages.reactactions.ReactAction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiscordUser {

    private long accountID;
    private String username;

    private List<Pronouns> pronouns = new LinkedList<>();
    private List<String> roledata = new LinkedList<>();

    private HashMap<Long, Message> messages = new HashMap<>();

    private DiscordUser(long id, String username){
        this.accountID = id;
        this.username = username;
    }

    public static DiscordUser getAccount(User user){
        //TODO: loop through existing saved accounts and check if id matches.
        DiscordUser d = new DiscordUser(user.getIdLong(), user.getName());
        d.sendReactRoles(user);
        return d;
    }

    private void sendReactRoles(User user) {
    }


    public List<Pronouns> getPronouns() {
        return pronouns;
    }

    public List<String> getRoledata() {
        return roledata;
    }

    public long getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }
}
