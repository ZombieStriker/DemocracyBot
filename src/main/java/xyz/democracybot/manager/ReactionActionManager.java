package xyz.democracybot.manager;

import xyz.democracybot.data.messages.reactactions.ReactAction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ReactionActionManager {

    private List<Class<? extends ReactAction>> actions = new LinkedList<>();

    public Class<? extends ReactAction> getReactionActionByName(String name){
        for(Class<? extends ReactAction> action : actions){
            if(action.getName().equals(name)){
                return action;
            }
        }
        return null;
    }

    public Constructor getFirstConstructor(Class<? extends ReactAction> clazz){
        for(Constructor constructor : clazz.getConstructors()){
            return constructor;
        }
        return null;
    }
    public ReactAction getReactionAction(String raw){
        String[] split = raw.split(" ");
        Class<? extends ReactAction> clazz = getReactionActionByName(split[0]);
        Constructor constructor = getFirstConstructor(clazz);
        if(constructor.getParameterCount() == 0){
            try {
                return (ReactAction) constructor.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }else{
            if(constructor.getParameterCount()==1){
                try {
                    constructor.newInstance(split[1]);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }else if(constructor.getParameterCount()==2){
                try {
                    constructor.newInstance(split[1], split[2]);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

}
