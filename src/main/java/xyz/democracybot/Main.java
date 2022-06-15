package xyz.democracybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String... args){
        try {
            JDA bot = JDABuilder.createDefault("ODc4ODM1NjI3NzE2NjQ4OTcw.GXai2d.j-jo2wjJh7Zi-gtRmROuS5qg69o6UMiQ0k_8Mk").addEventListeners(new DiscordListener()).build();

        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
}
