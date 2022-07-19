package xyz.democracybot.data;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.messages.reactactions.ReactAction;
import xyz.democracybot.ezyaml.EZYaml;
import xyz.democracybot.manager.EmojiManager;

import java.io.File;
import java.util.*;

public class Message {

    private Promise<Long> id;
    private HashMap<String, ReactAction> actions = new HashMap<>();

    public Message(Promise<Long> id) {
        this.id = id;
    }

    public Message(long id) {
        Promise promise = new Promise();
        promise.set(id);
        this.id = promise;
    }

    public Message(long id, File file) {
        this(new Promise<>(), file);
        this.id.set(id);
    }

    public Message(Promise<Long> id, File messageData) {
        this.id = id;
        EZYaml yaml = new EZYaml(messageData);
        for (String key : yaml.getConfigurationSection("react").getKeys()) {
            String reaction = yaml.getString("react." + key + ".type");
            ReactAction action = DemocracyBot.getInstance().getReactionActionManager().getReactionAction(reaction);
            addAction(EmojiManager.get(key),action);
        }
    }


    public void addAction(String react, ReactAction action) {
        this.actions.put(react, action);
    }

    public ReactAction getActionFor(String react) {
        if (actions.containsKey(react)) {
            return actions.get(react);
        }
        return null;
    }

    public Set<String> getReaction() {
        return actions.keySet();
    }

    public Promise<Long> getId() {
        return id;
    }

    public HashMap<String, ReactAction> getActions() {
        return actions;
    }

    public void save() {
        EZYaml yaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getMessage(id.get()));
        for(Map.Entry<String, ReactAction> e : actions.entrySet()) {
            yaml.set("react."+e.getKey()+".type",e.getValue().serialize());
        }
        yaml.save();
    }
}
