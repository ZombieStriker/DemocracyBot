package xyz.democracybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String... args){
        try {
            JDA bot = JDABuilder.createDefault("TOKEN KEY HERE").addEventListeners(new DiscordListener()).build();

        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
}
