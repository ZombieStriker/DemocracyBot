package xyz.democracybot.ezyaml;

import java.util.HashMap;
import java.util.Set;

public class EZConfigurationSection {

    HashMap<String, Object> data;

    public EZConfigurationSection(HashMap<String, Object> data){
        this.data = data;
    }

    public Set<String> getKeys(){
        return data.keySet();
    }

    public EZConfigurationSection getConfigurationSection(String key) {
        if (key.contains(".")) {
            String[] keys = key.split("\\.", 2);
            if (data.get(keys[0]) instanceof HashMap<?, ?>) {
                HashMap<String, Object> dd = (HashMap<String, Object>) data.get(keys[0]);
                EZConfigurationSection newConfig = new EZConfigurationSection(dd);
                return newConfig.getConfigurationSection(keys[1]);
            } else {
                return null;
            }
        } else {
            HashMap<String, Object> dd = (HashMap<String, Object>) data.get(key);
            EZConfigurationSection newConfig = new EZConfigurationSection(dd);
            return newConfig;
        }
    }

    public Object recursiveGet(String key){
        if(key.contains(".")){
            String[] keys = key.split("\\.",2);
            if(data.get(keys[0]) instanceof HashMap<?,?>) {
                HashMap<String, Object> dd = (HashMap<String, Object>) data.get(keys[0]);
                EZConfigurationSection newConfig = new EZConfigurationSection(dd);
                return newConfig.recursiveGet(keys[1]);
            }else{
                return data.get(keys[0]);
            }
        }else{
            return data.get(key);
        }
    }

    public String getString(String key){
        Object o = recursiveGet(key);
        if(o instanceof String){
            return (String) o;
        }
        return o+"";
    }
    public int getInt(String key){
        Object o = recursiveGet(key);
        if(o instanceof Integer){
            return (int) o;
        }
        return -1;
    }
    public long getLong(String key){
        Object o = recursiveGet(key);
        if(o instanceof Long){
            return (long) o;
        }
        return -1;
    }

    public boolean contains(String key) {
        if(key.contains(".")){
            String[] keys = key.split("\\.",2);
            if(data.get(keys[0]) instanceof HashMap<?,?>) {
                HashMap<String, Object> dd = (HashMap<String, Object>) data.get(keys[0]);
                EZConfigurationSection newConfig = new EZConfigurationSection(dd);
                return newConfig.contains(keys[1]);
            }else{
                return true;
            }
        }else{
            return data.containsKey(key);
        }
    }
}
