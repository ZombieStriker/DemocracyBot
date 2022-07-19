package xyz.democracybot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;
import xyz.democracybot.data.Promise;
import xyz.democracybot.manager.EmojiManager;
import xyz.democracybot.utils.DiscordMessageBuilder;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot())
            return;
        if(event.getChannelType()== ChannelType.PRIVATE){
            DiscordUser user = DemocracyBot.getInstance().getUser(event.getAuthor());
            DiscordMessageBuilder dmb = new DiscordMessageBuilder("Test");
            dmb.addReaction(EmojiManager.RED);
            Promise<Long> id = dmb.build(event.getAuthor());
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        if(event.getUser().isBot())
            return;

        DiscordUser account = DiscordUser.getAccount(event.getUser());
        //Pronouns and role select is sent in getAccount if new account;
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);
        if(event.getUser().isBot())
            return;

        Message message = new Message(event.getMessageIdLong(), DemocracyBot.getInstance().getFileManager().getMessage(event.getMessageIdLong()));
        if(message!=null)
        message.getActionFor(event.getReaction().getMessageId()).onAddReaction(DemocracyBot.getInstance().getUser(event.getUser()));
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        super.onMessageReactionRemove(event);
        if(event.getUser().isBot())
            return;
        Message message = new Message(event.getMessageIdLong(), DemocracyBot.getInstance().getFileManager().getMessage(event.getMessageIdLong()));
        if(message!=null)
        message.getActionFor(event.getReaction().getMessageId()).onRemoveReaction(DemocracyBot.getInstance().getUser(event.getUser()));
    }
}
