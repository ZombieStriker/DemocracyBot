package xyz.democracybot.data.messages.reactactions;

import xyz.democracybot.data.DiscordUser;
import xyz.democracybot.data.Message;

public class ReactBasicRoleAction implements ReactAction{

    private String roleid;

    public ReactBasicRoleAction(String roleid){
        this.roleid = roleid;
    }


    @Override
    public boolean onAddReaction(Message message, DiscordUser user) {
        user.getRoleData().add(roleid);
        return true;
    }

    @Override
    public boolean onRemoveReaction(Message message, DiscordUser user) {
        user.getRoleData().remove(roleid);
        return true;
    }

    @Override
    public String serialize() {
        return this.getClass().getName()+" "+roleid;
    }
}
