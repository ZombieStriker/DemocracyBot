package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;
import xyz.democracybot.data.Pronouns;
import xyz.democracybot.utils.DiscordMessageBuilder;

public class ReactPronouns implements ReactAction{

    private Pronouns pronouns;

    public ReactPronouns(String pronouns){
        this.pronouns = Pronouns.getPronoun(pronouns);
    }

    @Override
    public boolean onAddReaction(Message message, DiscordUser user) {
        user.getPronouns().add(pronouns);
        DiscordMessageBuilder reply = new DiscordMessageBuilder("You will now have the possibility to be referred to as "+pronouns.getPronoun1(user)+"/"+pronouns.getPronoun2(user)+"\n"
        +"(\""+pronouns.getPronoun1(user)+" is radical!\")");
        reply.build(user.getUserInstance());
        return false;
    }

    @Override
    public boolean onRemoveReaction(Message message, DiscordUser user) {
        user.getPronouns().remove(pronouns);
        DiscordMessageBuilder reply = new DiscordMessageBuilder("You will no longer be referred to as "+pronouns.getPronoun1(user)+"/"+pronouns.getPronoun2(user));
        reply.build(user.getUserInstance());
        return false;
    }

    @Override
    public String serialize() {
        return this.getClass().getName()+" "+pronouns.getPronoun1(null);
    }
}
