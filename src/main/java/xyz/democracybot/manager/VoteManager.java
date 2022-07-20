package xyz.democracybot.manager;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Promise;
import xyz.democracybot.data.Vote;
import xyz.democracybot.ezyaml.EZYaml;
import xyz.democracybot.utils.DiscordMessageBuilder;

import java.util.LinkedList;
import java.util.List;

public class VoteManager {

    private List<Vote> activeVotes = new LinkedList<>();

    public void registerVote(Vote vote){
        this.activeVotes.add(vote);
    }

    public List<Promise<Long>> sendNewVote(Vote vote) {
        StringBuilder message = new StringBuilder();
        message.append("New vote: " + vote.getName() + " \n \n Description: " + vote.getDescription());
        message.append("\n Vote options: \n");
        for (int i = 0; i < vote.getOptionsScore().length; i++) {
            String reaction = DemocracyBot.getInstance().getReactIds()[i];
            message.append("\n");
            message.append(reaction + ": " + vote.getOptionDesc()[i]);
        }
        message.append("\n\n");
        message.append("This vote will close in 24 hours.");
        DiscordMessageBuilder builder = new DiscordMessageBuilder(message.toString());
        List<Promise<Long>> votes = new LinkedList<>();
        for (DiscordUser du : DemocracyBot.getInstance().getUsers()) {
            votes.add(builder.build(du.getUserInstance()));
        }
        return votes;
    }


    public VoteManager(DemocracyBot instance) {
        EZYaml votesData = new EZYaml(instance.getFileManager().getVoteData());
        if (!votesData.isEmpty()) {
            for (String keyid : votesData.getKeys()) {
                long id = Long.parseLong(keyid);
                String name = votesData.getString(keyid + ".name");
                String desc = votesData.getString(keyid + ".desc");
                int count = votesData.getInt(keyid + ".total");
                long time = votesData.getLong(keyid + ".time");
                int[] array = new int[votesData.getInt(keyid + ".totaloptions")];
                String[] arraydesc = new String[votesData.getInt(keyid + ".totaloptions")];
                for (String option : votesData.getConfigurationSection(keyid + ".options").getKeys()) {
                    Integer optionid = Integer.parseInt(option);
                    array[optionid] = votesData.getInt(keyid + ".options." + option + ".v");
                    arraydesc[optionid] = votesData.getString(keyid + ".options." + option + ".d");
                }
                Vote vote = new Vote(name, desc, id, array, arraydesc, time);
                vote.setTotalScoreCount(count);
            }
        }
    }

    public void save() {
        EZYaml votesData = new EZYaml(DemocracyBot.getInstance().getFileManager().getVoteData());
        for (Vote vote : activeVotes) {
            long id = vote.getId();
            int total = vote.getTotalScoreCount();
            for (int i = 0; i < vote.getOptionsScore().length; i++) {
                votesData.set(id + ".options." + i + ".v", vote.getOptionsScore()[i]);
                votesData.set(id + ".options." + i + ".d", vote.getOptionDesc()[i]);
            }
            votesData.set(id + ".totaloptions", vote.getOptionsScore().length);
            votesData.set(id + ".total", vote.getTotalScoreCount());
            votesData.set(id + ".name", vote.getName());
            votesData.set(id + ".desc", vote.getDescription());
        }
        votesData.save();
    }

    public List<Vote> getActiveVotes() {
        return activeVotes;
    }
}
