package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;
import xyz.democracybot.data.Vote;
import xyz.democracybot.utils.DiscordMessageBuilder;

public class ReactVoteAction implements ReactAction{


    private long voteID;
    private int index;

    public ReactVoteAction(String voteid, String index){
        this.voteID = Long.parseLong(voteid);
        this.index = Integer.parseInt(index);
    }

    @Override
    public boolean onAddReaction(Message message, DiscordUser user) {
        Vote vote = null;
        for(Vote v : DemocracyBot.getInstance().getVoteManager().getActiveVotes()){
            if(v.getId()==voteID){
                vote = v;
                break;
            }
        }
        if(vote!=null) {
            vote.getOptionsScore()[index] = vote.getOptionsScore()[index] + 1;
            vote.setTotalScoreCount(vote.getTotalScoreCount() + 1);
            DiscordMessageBuilder builder = new DiscordMessageBuilder("Vote updated: Currently " + vote.getTotalScoreCount() + " votes have been submitted.");
            builder.build(user.getUserInstance());
        }else{
            System.out.println("Vote is not valid "+voteID);
        }
        return true;
    }

    @Override
    public boolean onRemoveReaction(Message message, DiscordUser user) {
        Vote vote = null;
        for(Vote v : DemocracyBot.getInstance().getVoteManager().getActiveVotes()){
            if(v.getId()==voteID){
                vote = v;
                break;
            }
        }
        if(vote!=null){
            vote.getOptionsScore()[index]=vote.getOptionsScore()[index]-1;
            vote.setTotalScoreCount(vote.getTotalScoreCount()-1);
        }
        DiscordMessageBuilder builder = new DiscordMessageBuilder("Vote updated: Currently "+vote.getTotalScoreCount()+" votes have been submitted.");
        builder.build(user.getUserInstance());
        return true;
    }
    @Override
    public String serialize() {
        return this.getClass().getName()+" "+voteID+" "+index;
    }
}
