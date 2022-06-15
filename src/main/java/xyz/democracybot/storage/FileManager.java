package xyz.democracybot.storage;

import java.io.File;
import java.net.URISyntaxException;

public class FileManager {

    private File folder;

    public FileManager(){
        folder = getJar().getParentFile();
    }

    public File getUserDataFolder(){
        File f = new File(getFolder(),"userdata");
        if(!f.exists())
            f.mkdirs();
        return f;
    }
    public File getUserData(long userid){
        return new File(getUserDataFolder(),userid+".yml");
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
