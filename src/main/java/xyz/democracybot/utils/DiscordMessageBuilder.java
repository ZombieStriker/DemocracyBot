package xyz.democracybot.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import xyz.democracybot.data.Promise;

import java.util.LinkedList;
import java.util.List;

public class DiscordMessageBuilder {


    private List<String> reactRoles = new LinkedList<>();
    private String string;

    public DiscordMessageBuilder(String string) {
        this.string = string;
    }

    public DiscordMessageBuilder addReaction(String reaction) {
        this.reactRoles.add(reaction);
        return this;
    }

    public Promise<Long> build(User user) {
        Promise<Long> messageID = new Promise<>();
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage(string).queue(message -> {
                for (String react : reactRoles) {
                    message.addReaction(react).queue();
                }
                messageID.set(message.getIdLong());
            });
        }));
        return messageID;
    }
}
