package main;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 08.11.14.
 */
public class Item {
    private String name;

    public Item(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
