package xyz.democracybot.manager;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.command.CommandExecutor;
import xyz.democracybot.data.DiscordUser;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String, CommandExecutor> commands = new HashMap<>();

    public CommandManager(DemocracyBot instance) {

    }

    public void registerCommandExecutor(String command, CommandExecutor executor) {
        this.commands.put(command, executor);
    }

    public void call(String message, DiscordUser user) {
        String[] split = message.split(" ");
        String command = split[0].substring(1);
        if(command.contains("\n")){
            command = command.split("\n",2)[0];
        }

        String[] args = new String[split.length - 1];
        for (int i = 1; i < split.length; i++) {
            args[i - 1] = split[i];
        }
        String[] splitn = message.split("\n");
        if(commands.containsKey(command)) {
            commands.get(command).execute(user, args, splitn);
        }

    }

    public boolean containsCommand(String message) {
        String command = message.substring(1).split(" ", 2)[0].split("\n",2)[0];
        if (commands.containsKey(command))
            return true;
        return false;
    }
}
