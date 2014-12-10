package pl.edu.amu.dji.jms.lab4.message;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
public class SalesInformation {
    private String name;
    private double price;

    public SalesInformation(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
