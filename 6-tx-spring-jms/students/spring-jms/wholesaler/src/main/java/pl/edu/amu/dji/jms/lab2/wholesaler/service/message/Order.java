package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

/**
 * Created by s362631 on 13.11.14.
 */
public class Order {
    private int quantity;
    private String retailerID;

    public Order(int quantity, String retailerID) {
        this.setQuantity(quantity);
        this.setRetailerID(retailerID);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(String retailerID) {
        this.retailerID = retailerID;
    }
}
