package xyz.democracybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import xyz.democracybot.ezyaml.EZYaml;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    public static void main(String... args){
        try {
            EZYaml ezYaml = new EZYaml(DemocracyBot.getInstance().getFileManager().getTokenFile());
            if(!ezYaml.contains("token")){
                DemocracyBot.getInstance().getFileManager().getTokenFile().createNewFile();
                System.out.println("Please put the token in the token.yml as \"token: <token>\".");
                return;
            }
            String token = ezYaml.getString("token");
            JDA bot = JDABuilder.createDefault(token).addEventListeners(new DiscordListener()).build();

        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
