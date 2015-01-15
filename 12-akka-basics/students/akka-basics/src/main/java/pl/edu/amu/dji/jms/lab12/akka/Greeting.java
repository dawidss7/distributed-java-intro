package pl.edu.amu.dji.jms.lab12.akka;

import java.io.Serializable;

public final class Greeting implements Serializable {
    private String who;

    public Greeting(String who) {
        this.who = who;
    }

    public String getWho() {
        return who;
    }
}
