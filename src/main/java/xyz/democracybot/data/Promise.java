package xyz.democracybot.data;

public class Promise<T> {

    private T object = null;
    private boolean p = false;

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
    }
}
