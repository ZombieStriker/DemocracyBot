package xyz.democracybot.data;

import java.util.LinkedList;
import java.util.List;

public class Pronouns {

    private static final List<Pronouns> list = new LinkedList<>();

    private String pronoun1;
    private String pronoun2;
    private PronounBase base;

    static{
        list.add(new Pronouns(PronounBase.HE,"he","him"));
        list.add(new Pronouns(PronounBase.SHE,"she","her"));
        list.add(new Pronouns(PronounBase.THEY,"they","them"));
        list.add(new Pronouns(PronounBase.IT,"it","its"));
        list.add(new Pronouns(PronounBase.NEO,null,null));
    }

    private Pronouns(PronounBase base, String v1, String v2){
        this.base = base;
        this.pronoun1 = v1;
        this.pronoun2 = v2;
    }

    public String getPronoun1(DiscordUser user) {
        if(user!=null&&pronoun1==null){
            return user.getUsername();
        }
        return pronoun1;
    }

    public String getPronoun2(DiscordUser user) {
        if(user!=null&&pronoun2==null){
            return user.getUsername();
        }
        return pronoun2;
    }

    public PronounBase getBase() {
        return base;
    }

    public static List<Pronouns> getPronouns(){
        return list;
    }

    public static Pronouns getPronoun(String v1){
        for(Pronouns pn : list){
            if(pn.getBase()==PronounBase.NEO)
                continue;
            if(pn.getPronoun1(null).equalsIgnoreCase(v1)||pn.getPronoun2(null).equalsIgnoreCase(v1)){
                return pn;
            }
        }
        for(Pronouns pn : list) {
            if (pn.getPronoun1(null)==null||pn.getPronoun2(null)==null)
                return pn;
        }
        return null;
    }

    enum PronounBase{
        HE,
        SHE,
        THEY,
        IT,
        NEO;
    }
}
