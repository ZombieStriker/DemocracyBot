package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.data.DiscordUser;

public class ReactBasicRoleAction implements ReactAction{

    private String roleid;

    public ReactBasicRoleAction(String roleid){
        this.roleid = roleid;
    }


    @Override
    public boolean onAddReaction(DiscordUser user) {

        return true;
    }

    @Override
    public boolean onRemoveReaction(DiscordUser user) {
        return true;
    }
}
