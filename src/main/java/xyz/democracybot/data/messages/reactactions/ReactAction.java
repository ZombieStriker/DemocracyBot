package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.data.DiscordUser;

public interface ReactAction {

    /**
     * Called when the message is reacted to.
     * @return whether the action was a success
     */
    boolean onAddReaction(DiscordUser user);

    boolean onRemoveReaction(DiscordUser user);
}
