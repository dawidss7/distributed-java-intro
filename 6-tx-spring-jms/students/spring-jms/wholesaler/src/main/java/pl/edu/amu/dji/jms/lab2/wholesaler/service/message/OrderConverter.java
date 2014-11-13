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
@Component(value = "orderConverter")
public class OrderConverter implements MessageConverter{

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {

        MapMessage mapMessage = (MapMessage) message;
        int quantity = mapMessage.getInt("quantity");
        String retailerID = mapMessage.getString("retailerID");
        return new Order(quantity, retailerID);
    }
}
