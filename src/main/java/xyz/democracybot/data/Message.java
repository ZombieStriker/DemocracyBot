package xyz.democracybot.data;

import xyz.democracybot.data.messages.reactactions.ReactAction;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Message {

    private long id;
    private HashMap<String, ReactAction> actions = new HashMap<>();

    public Message(long id){
        this.id = id;
    }

    public Message(long id, File messageData){

    }


    public void addAction(String react, ReactAction action){
        this.actions.put(react,action);
    }
    public ReactAction getActionFor(String react){
        if(actions.containsKey(react)){
            return actions.get(react);
        }
        return null;
    }
    public Set<String> getReaction(){
        return actions.keySet();
    }

}
