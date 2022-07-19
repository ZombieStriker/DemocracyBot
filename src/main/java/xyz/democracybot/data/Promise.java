package xyz.democracybot.data;

public class Promise<T> {

    private T object = null;
    private boolean p = false;

    private CallableAction callableAction;

    public Promise(){
    }

    public T get(){
        return object;
    }

    public boolean isFullfilled(){
        return p;
    }
    public void set(T object){
        this.object = object;
        p=true;
        if(callableAction!=null )
        callableAction.onCall();
    }

    public void setCallableAction(CallableAction callableAction) {
        this.callableAction = callableAction;
    }

    public CallableAction getCallableAction() {
        return callableAction;
    }
}
