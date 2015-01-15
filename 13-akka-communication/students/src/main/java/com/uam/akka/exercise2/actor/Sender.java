package com.uam.akka.exercise2.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.uam.akka.exercise2.message.Question;
import com.uam.akka.exercise2.message.Answer;
import com.uam.akka.exercise2.message.Start;

public class Sender extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public Sender() {
getContext().actorOf(Props.create(com.uam.akka.exercise2.actor.Receiver.class), "receiver");
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Start){
            getContext().children().iterator().next().tell(new Question("Pytanko"), getSelf());
        }else if (o instanceof Answer){
            log.info(((Answer) o).getText());
            getContext().system().shutdown();
        }
    }
}
