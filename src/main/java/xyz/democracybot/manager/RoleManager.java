package xyz.democracybot.manager;

import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.Role;
import xyz.democracybot.ezyaml.EZYaml;

import java.util.LinkedList;
import java.util.List;

public class RoleManager {

    private List<Role> roles = new LinkedList<>();

    public RoleManager(){
        EZYaml yaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getRolesFile());
        for(String string : yaml.getKeys()){
            roles.add(new Role(string, yaml.getString(string)));
        }
    }
}
