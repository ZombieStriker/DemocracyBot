package xyz.democracybot.command;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.*;
import xyz.democracybot.data.messages.reactactions.ReactVoteAction;
import xyz.democracybot.manager.ReactionActionManager;
import xyz.democracybot.utils.DiscordMessageBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VoteCreateCommand implements CommandExecutor {
    @Override
    public void execute(DiscordUser user, String[] args, String[] lines) {
        if (lines.length < 4) {
            new DiscordMessageBuilder("Invalid format. Please use :\n\n!cvote\n<vote name>\n<description>\n<ote options #1>\n<ote options#2>\n<ote options#3>\n...").build(user.getUserInstance());
            return;
        }
        String name = lines[1];
        String description = lines[2];

        List<String> descriptionVotes = new LinkedList<>();
        for (int i = 3; i < lines.length; i++) {
            descriptionVotes.add(lines[i]);
        }


        Vote vote = new Vote(name, description, ThreadLocalRandom.current().nextLong(), descriptionVotes.toArray(new String[descriptionVotes.size()]), System.currentTimeMillis());
        List<Promise<Long>> messages = DemocracyBot.getInstance().getVoteManager().sendNewVote(vote);
        DemocracyBot.getInstance().getVoteManager().registerVote(vote);

        for (Promise<Long> l : messages) {
            if (l.isFullfilled()) {
                Message message = DemocracyBot.getInstance().getMessage(l.get());
                for (int i = 0; i < descriptionVotes.size(); i++) {
                    message.addAction(DemocracyBot.getInstance().getReactIds()[i], new ReactVoteAction(vote.getId() + "", "" + i));
                }
                message.save();
            } else {
                l.setCallableAction(new CallableAction() {
                    @Override
                    public void onCall() {
                        Message message = DemocracyBot.getInstance().getMessage(l.get());
                        for (int i = 0; i < descriptionVotes.size(); i++) {
                            message.addAction(DemocracyBot.getInstance().getReactIds()[i], new ReactVoteAction(vote.getId() + "", "" + i));
                        }
                        message.save();
                    }
                });
            }
        }
    }
}
