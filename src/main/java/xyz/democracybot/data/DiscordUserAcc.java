package xyz.democracybot.data;

import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.managers.EmoteManager;
import net.dv8tion.jda.internal.requests.Route;
import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.messages.MessageTypes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiscordUserAcc {

    private long accountID;
    private String username;

    private List<Pronouns> pronouns = new LinkedList<>();
    private List<RoleData> roledata = new LinkedList<>();

    private HashMap<MessageTypes, Long> roleReactMessages = new HashMap<>();

    private DiscordUserAcc(long id, String username){
        this.accountID = id;
        this.username = username;
    }

    public static DiscordUserAcc getAccount(User user){
        //TODO: loop through existing saved accounts and check if id matches.
        DiscordUserAcc d = new DiscordUserAcc(user.getIdLong(), user.getName());
        d.sendReactRoles(user);
        return d;
    }

    private void sendReactRoles(User user) {
        user.openPrivateChannel().queue((privateChannel -> {
          privateChannel.sendMessage(DemocracyBot.getInstance().getTextManager().getWelcomeDm()).queue();
          privateChannel.sendMessage(DemocracyBot.getInstance().getTextManager().getPronounsDM()).queue(message -> {
              roleReactMessages.put(MessageTypes.PRONOUNS,message.getIdLong());
              message.addReaction("one").queue(unused1 -> {
                  message.addReaction("two").queue(unused2 -> {
                      message.addReaction("three").queue(unused3 -> {
                          message.addReaction("four").queue(unused4 -> {
                              message.addReaction("five").queue(unused5 -> {
                                  message.addReaction("six").queue(unused6 -> {
                                  });
                              });
                          });
                      });
                  });
              });
          });



        }));
    }

    public HashMap<MessageTypes, Long> getRoleReactMessages() {
        return roleReactMessages;
    }

    public List<Pronouns> getPronouns() {
        return pronouns;
    }

    public List<RoleData> getRoledata() {
        return roledata;
    }

    public long getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }
}
