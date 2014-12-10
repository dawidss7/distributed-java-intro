package pl.edu.amu.dji.jms.lab4.message;

import pl.edu.amu.dji.jms.lab4.Product;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
public class FullProductList {
    private List<Product> productList = Collections.EMPTY_LIST;

    public FullProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
