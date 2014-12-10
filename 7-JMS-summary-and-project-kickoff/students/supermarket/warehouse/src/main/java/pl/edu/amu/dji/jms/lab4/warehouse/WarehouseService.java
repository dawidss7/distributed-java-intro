package pl.edu.amu.dji.jms.lab4.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Product;
import pl.edu.amu.dji.jms.lab4.message.FullProductList;
import pl.edu.amu.dji.jms.lab4.message.PriceChange;

import javax.jms.Destination;
import java.util.List;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Service("warehouseService")
public class WarehouseService {
    @Autowired
    @Qualifier("fullProductListJmsTemplate")
    private JmsTemplate fullProductListJmsTemplate;

    @Autowired
    @Qualifier("priceChangeJmsTemplate")
    private JmsTemplate priceChangeJmsTemplate;

    @Autowired
    @Qualifier("fullProductListTopic")
    private Destination fullProductListTopic;

    @Autowired
    @Qualifier("priceChangeTopic")
    private Destination priceChangeTopic;

    @Transactional
    public void sendPriceChange(final String productName, final double price){
        PriceChange priceChange = new PriceChange(productName, price);
        priceChangeJmsTemplate.convertAndSend(priceChange);
    }
    @Transactional
    public void sendFullProductList(final List<Product> productList){
        FullProductList fullProductList = new FullProductList(productList);
        fullProductListJmsTemplate.convertAndSend(fullProductList);
    }
}
