package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by s362631 on 13.11.14.
 */
@Component(value = "offerConverter")
public class OfferConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        MapMessage message = session.createMapMessage();
        message.setDouble("price", ((Offer)o).getPrice());
        message.setJMSReplyTo(((Offer)o).getReplyTo());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException();
    }
}
