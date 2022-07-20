package xyz.democracybot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import xyz.democracybot.data.*;
import xyz.democracybot.data.messages.reactactions.ReactAction;
import xyz.democracybot.ezyaml.EZYaml;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        if (event.getChannelType() == ChannelType.PRIVATE) {
            DiscordUser user = DemocracyBot.getInstance().getUser(event.getAuthor());
            if (event.getMessage().getContentRaw().startsWith("!")) {
                if (DemocracyBot.getInstance().getCommandManager().containsCommand(event.getMessage().getContentRaw())) {
                    DemocracyBot.getInstance().getCommandManager().call(event.getMessage().getContentRaw(), user);
                }
            }
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        EZYaml ezYaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getServers());
        ezYaml.set("servers."+event.getGuild().getId()+".name",event.getGuild().getName());
        DemocracyBot.getInstance().getServers().add(new DiscordServer(event.getGuild().getId(), event.getGuild().getName()));
        ezYaml.save();
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        if (event.getUser().isBot())
            return;

        //DiscordUser account = DiscordUser.getAccount(event.getUser());
        //Pronouns and role select is sent in getAccount if new account;
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);
        if (event.getUser().isBot())
            return;

        Message message = DemocracyBot.getInstance().getMessage(event.getMessageIdLong());
        if (message != null) {
            ReactAction action = message.getActionFor(event.getReaction().getReactionEmote().getEmoji());
            if (action != null) {
                action.onAddReaction(message, DemocracyBot.getInstance().getUser(event.getUser()));
            }else{
                System.out.println("Failed to find action "+event.getReactionEmote().getEmoji());
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        super.onMessageReactionRemove(event);
        if (event.getUser().isBot())
            return;
        Message message = DemocracyBot.getInstance().getMessage(event.getMessageIdLong());
        if (message != null) {
            ReactAction action = message.getActionFor(event.getReaction().getReactionEmote().getEmoji());
            if (action != null)
                action.onRemoveReaction(message, DemocracyBot.getInstance().getUser(event.getUser()));
        }
    }
}
