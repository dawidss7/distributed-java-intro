package pl.edu.amu.dji.jms.lab12.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class GreetingApp {
    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("GreetingSystem");
        ActorRef greeter = system.actorOf(Props.create(GreetingActor.CREATOR));
        greeter.tell(new Greeting("UAM"), ActorRef.noSender());

    }
}
