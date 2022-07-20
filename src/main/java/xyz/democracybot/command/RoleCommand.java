package xyz.democracybot.command;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.*;
import xyz.democracybot.data.messages.reactactions.ReactPronouns;
import xyz.democracybot.manager.EmojiManager;
import xyz.democracybot.utils.DiscordMessageBuilder;

public class RoleCommand implements CommandExecutor{
    @Override
    public void execute(DiscordUser user, String[] args, String[] lines) {
        DiscordMessageBuilder messageBuilder = new DiscordMessageBuilder("Please react to the following messages to assign your roles.");
        messageBuilder.build(user.getUserInstance());


        StringBuilder sb = new StringBuilder();
        sb.append("React to this message with "+ DemocracyBot.getInstance().getReactIds()[0]+" to be referred to as He/Him.\n");
        sb.append("React to this message with "+ DemocracyBot.getInstance().getReactIds()[1]+" to be referred to as She/Her.\n");
        sb.append("React to this message with "+ DemocracyBot.getInstance().getReactIds()[2]+" to be referred to as Them/They.\n");
        sb.append("React to this message with "+ DemocracyBot.getInstance().getReactIds()[3]+" to be referred to as It/its.\n");
        sb.append("React to this message with "+ DemocracyBot.getInstance().getReactIds()[4]+" to be referred to by your username.\n");

        DiscordMessageBuilder pronouns = new DiscordMessageBuilder(sb.toString());
        Promise pronounslong = pronouns.build(user.getUserInstance());
        pronounslong.setCallableAction(new CallableAction() {
            @Override
            public void onCall() {
                Message message = DemocracyBot.getInstance().getMessage((Long) pronounslong.get());
                if(message!=null){
                    for(int i = 0 ; i < Pronouns.getPronouns().size();i++){
                        Pronouns pro = Pronouns.getPronouns().get(i);
                        message.addAction(DemocracyBot.getInstance().getReactIds()[i],new ReactPronouns(pro.getPronoun1(user)));
                    }
                    message.save();
                }
            }
        });
    }
}
