package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;

public interface ReactAction {

    /**
     * Called when the message is reacted to.
     * @return whether the action was a success
     */
    boolean onAddReaction(Message message, DiscordUser user);

    boolean onRemoveReaction(Message message, DiscordUser user);

    String serialize();
}
