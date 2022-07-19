package xyz.democracybot.manager;

import net.dv8tion.jda.internal.utils.tuple.Pair;
import xyz.democracybot.DemocracyBot;
import xyz.democracybot.data.Message;
import xyz.democracybot.ezyaml.EZYaml;

import java.io.File;
import java.util.HashMap;

public class MessageManager {

    private static final String WELCOME_MESSAGE = "Hello, Welcome Message!";
    private HashMap<String, MessageData> messageData = new HashMap<>();

    public MessageManager(DemocracyBot instannce){
        File messages = instannce.getFileManager().getTextMessagesFile();
        if(messages.exists()) {
            EZYaml ezYaml = new EZYaml(messages);
            for (String key : ezYaml.getKeys()) {
                MessageData md = new MessageData(ezYaml.getString(key + ".text"));
                messageData.put(key, md);
                for (String react : ezYaml.getConfigurationSection(key + ".reactions").getKeys()) {
                    md.addReact(react, ezYaml.getString(key + ".reactions." + react));
                }
            }
        }else{
            generateText("welcome",WELCOME_MESSAGE, generatePair("one","dummy"));
        }
    }

    public Pair<String, String> generatePair(String string1, String string2){
        return new Pair<String, String>() {
            @Override
            public String getLeft() {
                return string1;
            }

            @Override
            public String getRight() {
                return string2;
            }
        };
    }

    public void generateText(String name, String text, Pair<String, String>... reactions){
        MessageData md = new MessageData(text);
        for(Pair<String, String> p : reactions){
            md.reactTypes.put(p.getLeft(),p.getRight());
        }
        messageData.put(name, md);

    }

    public class MessageData{

        private String text;
        private HashMap<String, String> reactTypes = new HashMap<>();

        public MessageData(String text){
            this.text = text;
        }
        public void addReact(String reactKey, String reactionAction){
            this.reactTypes.put(reactKey,reactionAction);
        }
    }
}
