package pl.edu.amu.dji.jms.lab2.retailer.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            double price = mapMessage.getDouble("price");
            if (maxPrice.compareTo(price)==1){
                jmsTemplate.send(message.getJMSReplyTo(), new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                            MapMessage replyMessage = session.createMapMessage();
                            replyMessage.setString("retailerId", getClass().getName());
                            replyMessage.setInt("quantity", 214);
                            return replyMessage;
                    }
                });
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
