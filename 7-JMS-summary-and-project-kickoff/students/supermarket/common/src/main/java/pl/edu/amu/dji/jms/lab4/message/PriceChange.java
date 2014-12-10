package pl.edu.amu.dji.jms.lab4.message;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
public class PriceChange {
    private String productName;
    private Double newPrice;

    public PriceChange(String productName, Double newPrice) {
        this.productName = productName;
        this.newPrice = newPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
