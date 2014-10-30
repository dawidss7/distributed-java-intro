package pl.edu.amu.dji.jms.lab1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloMain {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        /*
        Create Connection instance from ConnectionFactory

        Create Session instance from connection object.
        - session shouldn't by transactional and should by in auto acknowledge mode (Session.AUTO_ACKNOWLEDGE).

        Create Destination queue from session (check Session class and createQueue method)
        - queue name should be "SayHelloQueue"


        Create Destination topic instance from session (check Session class and createTopic method)
        - topic name should be "SayHelloTopic"
         */

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("SayHelloQueue");
        //Destination topic = session.createTopic("SayHelloTopic");
        MessageConsumer consumer = session.createConsumer(queue, "dots is not null");

        /*
        Create MessageConsumer instance from session (check Session class and createConsumer method)

        Implement onMessage in MessageListener interface
        - check if message is in proper type (see message type in Say class) by instanceof
        - get text from message (remember to cast message to proper type)
        - print message text to sysout
        - don't forget to handle JMSException
         */
        MessageListener helloListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                boolean dots=false;
                try {
                    dots=message.getBooleanProperty("dots");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println("dots: "+dots);
                if(! (message instanceof TextMessage)){
                    throw new UnsupportedOperationException();
                }
                String text="";
                try {
                    text = ((TextMessage) message).getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(text);
            }
        };
        consumer.setMessageListener(helloListener);
        //Set MessageListener implementation as a message listener in MessageConsumer

        connection.start();
    }
}
