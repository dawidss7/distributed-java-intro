package pl.edu.amu.dji.jms.lab4.message;

import com.google.common.collect.Lists;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Product;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Component("fullProductListConverter")
public class FullProductListConverter implements MessageConverter{
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        ObjectMessage message = session.createObjectMessage();
        message.setObject(Lists.newArrayList(((FullProductList) o).getProductList()));
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        List<Product> productList = (List<Product>)objectMessage.getObject();
        return new FullProductList(productList);
    }
}
