package xyz.democracybot.ezyaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;

public class EZYaml extends EZConfigurationSection{

    private File file;

    public EZYaml(File file){
        super(null);
        this.file = file;
        if(!file.getParentFile().exists()){
            file.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            InputStream is = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(){
        Yaml yaml = new Yaml();
        String text = yaml.dumpAsMap(data);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
