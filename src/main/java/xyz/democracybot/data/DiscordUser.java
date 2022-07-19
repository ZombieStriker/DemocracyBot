package xyz.democracybot.data;

import net.dv8tion.jda.api.entities.User;
import xyz.democracybot.DemocracyBot;
import xyz.democracybot.ezyaml.EZYaml;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiscordUser {

    private String accountID;
    private String username;

    private List<Pronouns> pronouns = new LinkedList<>();
    private List<String> roledata = new LinkedList<>();

    private HashMap<Long, Message> messages = new HashMap<>();

    private User userinstance;

    private DiscordUser(DemocracyBot democracyBot, String id, String username){
        this.accountID = id;
        this.username = username;
        democracyBot.getJda().retrieveUserById(id).queue(user -> userinstance=user);
        democracyBot.getUsers().add(this);
    }
    private DiscordUser(User user){
        this.accountID = user.getId();
        this.username = user.getName();
        userinstance=user;
        DemocracyBot.getInstance().getUsers().add(this);
    }

    public static DiscordUser create(DemocracyBot democracyBot,File f){
        EZYaml ezYaml = new EZYaml(f);
        return new DiscordUser(democracyBot, f.getName().substring(0,f.getName().length()-4),ezYaml.getString("username"));
    }

    public static DiscordUser getAccount(User user){
        for(DiscordUser u: DemocracyBot.getInstance().getUsers()){
            if(u.getAccountID().equals(user.getId()))
                return u;
        }
        File file = DemocracyBot.getInstance().getFileManager().getUserData(user.getId());
        if(file.exists()) {
            DiscordUser d = new DiscordUser(user);
            d.sendReactRoles(user);
            DemocracyBot.getInstance().getUsers().add(d);

            EZYaml yaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getUserData(user.getId()));
            //TODO: Load info

            return d;
        }else{
            DiscordUser d = new DiscordUser(user);
            d.sendReactRoles(user);
            DemocracyBot.getInstance().getUsers().add(d);

            EZYaml yaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getUserData(user.getId()));
            yaml.set("username",user.getName());
            yaml.save();
            return d;
        }
    }

    private void sendReactRoles(User user) {
    }


    public List<Pronouns> getPronouns() {
        return pronouns;
    }

    public List<String> getRoleData() {
        return roledata;
    }

    public User getUserInstance() {
        if(userinstance == null){
            DemocracyBot.getInstance().getJda().retrieveUserById(accountID).queue(user -> userinstance=user);
        }
        return userinstance;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }
}
