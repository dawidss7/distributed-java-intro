package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab2.wholesaler.service.message.Order;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
@Service("orderService")
public class OrderService{

    @Transactional
    public void order(Order order) {
            System.out.println("Ordered quantity: " + order.getQuantity() + " by " + order.getRetailerID());
    }
}
