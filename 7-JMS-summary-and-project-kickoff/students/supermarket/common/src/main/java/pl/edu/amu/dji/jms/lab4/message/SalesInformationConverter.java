package pl.edu.amu.dji.jms.lab4.message;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Component("salesInformationConverter")
public class SalesInformationConverter implements MessageConverter{

    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        MapMessage message = session.createMapMessage();
        message.setDouble("price", ((SalesInformation) o).getPrice());
        message.setString(("name"), ((SalesInformation) o).getName());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        String name = mapMessage.getString("name");
        double price = mapMessage.getDouble("price");
        return new SalesInformation(name, price);
    }
}
