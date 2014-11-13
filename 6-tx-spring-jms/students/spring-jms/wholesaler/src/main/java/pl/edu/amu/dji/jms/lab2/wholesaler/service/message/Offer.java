package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;


import javax.jms.Destination;

/**
 * Created by s362631 on 13.11.14.
 */
public class Offer {
    private Double price;
    private Destination replyTo;

    public Offer(Double price, Destination replyTo) {
        this.setPrice(price);
        this.setReplyTo(replyTo);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Destination getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Destination replyTo) {
        this.replyTo = replyTo;
    }
}
