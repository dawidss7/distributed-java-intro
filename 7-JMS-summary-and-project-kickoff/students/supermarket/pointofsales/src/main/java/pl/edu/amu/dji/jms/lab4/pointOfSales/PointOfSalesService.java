package pl.edu.amu.dji.jms.lab4.pointOfSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Product;
import pl.edu.amu.dji.jms.lab4.message.FullProductList;
import pl.edu.amu.dji.jms.lab4.message.PriceChange;
import pl.edu.amu.dji.jms.lab4.message.SalesInformation;

import javax.jms.Destination;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Service("pointOfSalesService")
public class PointOfSalesService {

    private Map<String, Product> products = new HashMap<>();

    @Autowired
    @Qualifier("reportJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("reportQueue")
    private Destination reportQueue;

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    @Transactional
    public void updatePrice(final PriceChange priceChange) {
        Product product = products.get(priceChange.getProductName());
        if (product == null) {
            return;
        }
        product.setPrice(priceChange.getNewPrice());
        products.put(product.getName(), product);
    }

    @Transactional
    public void updateProductList(final FullProductList productList) {
        products.clear();
        productList.getProductList().forEach((product) -> products.put(product.getName(), product));
    }

    @Transactional
    public void sendSalesInformation(final Product product) {
        jmsTemplate.convertAndSend(new SalesInformation(product.getName(), product.getPrice()));
    }
}
