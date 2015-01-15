package pl.edu.amu.dji.jms.lab12.akka;

import akka.actor.Actor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class GreetingActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor create() throws Exception {
            return new GreetingActor();
        }
    };

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting) {
            log.info("Hello " + ((Greeting) message).getWho());
        }

    }
}
