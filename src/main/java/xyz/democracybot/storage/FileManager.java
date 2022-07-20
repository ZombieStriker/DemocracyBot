package xyz.democracybot.storage;

import xyz.democracybot.DemocracyBot;

import java.io.File;
import java.net.URISyntaxException;

public class FileManager {

    private File folder;

    public FileManager(DemocracyBot democracyBot){
        folder = getJar().getParentFile();
    }

    public File getUserDataFolder(){
        File f = new File(getFolder(),"userdata");
        if(!f.exists())
            f.mkdirs();
        return f;
    }
    public File getMessageDataFolder(){
        File f = new File(getFolder(),"messages");
        if(!f.exists())
            f.mkdirs();
        return f;
    }
    public File getUserData(String userid){
        return new File(getUserDataFolder(),userid+".yml");
    }
    public File getMessage(long messageid){
        return new File(getMessageDataFolder(),messageid+".yml");
    }

    public File getRolesFile(){
        return new File(folder,"RoleNames.yml");
    }
    public File getTokenFile(){
        return new File(folder,"token.yml");
    }
    public File getVoteData(){
        return new File(folder,"votes.yml");
    }
    public File getServers(){
        return new File(folder,"servers.yml");
    }
    public File getTextMessagesFile(){
        return new File(folder,"Messages.yml");
    }

    public File getFolder() {
        return folder;
    }

    public File getJar(){
        try {
            return new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
