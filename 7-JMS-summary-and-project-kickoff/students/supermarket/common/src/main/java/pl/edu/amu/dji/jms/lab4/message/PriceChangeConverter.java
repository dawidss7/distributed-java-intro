package pl.edu.amu.dji.jms.lab4.message;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Product;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Component("priceChangeConverter")
public class PriceChangeConverter implements MessageConverter {

    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        MapMessage message = session.createMapMessage();
        message.setDouble("newPrice", ((PriceChange) o).getNewPrice());
        message.setString(("productName"), ((PriceChange) o).getProductName());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        String productName = mapMessage.getString("productName");
        double newPrice = mapMessage.getDouble("newPrice");
        return new PriceChange(productName, newPrice);
    }
}


