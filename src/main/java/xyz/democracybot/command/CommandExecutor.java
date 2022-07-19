package xyz.democracybot.command;

import xyz.democracybot.data.DiscordUser;

public interface CommandExecutor {

    void execute(DiscordUser user, String[] args, String[] lines);
}
