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
    }

    private Pronouns(PronounBase base, String v1, String v2){
        this.base = base;
        this.pronoun1 = v1;
        this.pronoun2 = v2;
    }

    public String getPronoun1() {
        return pronoun1;
    }

    public String getPronoun2() {
        return pronoun2;
    }

    public static Pronouns getPronoun(String v1, String v2){
        for(Pronouns pn : list){
            if(pn.getPronoun1().equalsIgnoreCase(v1)&&pn.getPronoun2().equalsIgnoreCase(v2)){
                return pn;
            }
        }
        Pronouns pn = new Pronouns(PronounBase.NEO,v1,v2);
        list.add(pn);
        return pn;
    }

    enum PronounBase{
        HE,
        SHE,
        THEY,
        IT,
        NEO;
    }
}
