package pl.edu.amu.dji.jms.lab4;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.warehouse.WarehouseService;

import javax.xml.transform.sax.SAXSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WarehouseApp {

    public static final String EXIT = "exit";
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, Product> products = new HashMap<>();
    private static ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static WarehouseService warehouseService = (WarehouseService) context.getBean("warehouseService");

    private static void drawOptions() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("AVAILABLE PRODUCTS");
        products.values().forEach((product) -> System.out.println(product.getName() + " " + product.getPrice()+"$"));
        System.out.println("---------------------------------");
        System.out.println("OPTIONS");
        System.out.println("1. ADD PRODUCT; 2. REMOVE PRODUCT; 3. CHANGE PRICE; 4. SEND FULL PRODUCT LIST;");
    }
    private static void addProductOption() throws IOException {
        System.out.print("Enter product name: ");
        String name = bufferedReader.readLine();
        System.out.print("Enter product price: ");
        String priceString = bufferedReader.readLine();
        double price= Double.valueOf(priceString);
        products.put(name, new Product(name, price));
        warehouseService.sendFullProductList(Lists.newArrayList(products.values()));
    }
    private static void removeProductOption() throws IOException {
        System.out.print("Enter product name: ");
        String name = bufferedReader.readLine();
        products.remove(name);
        warehouseService.sendFullProductList(Lists.newArrayList(products.values()));
    }

    private static void changePriceOption() throws IOException {
        System.out.print("Enter product name: ");
        String name = bufferedReader.readLine();
        System.out.print("Enter new product price: ");
        String priceString = bufferedReader.readLine();
        double price= Double.valueOf(priceString);
        products.put(name, new Product(name, price));
        warehouseService.sendPriceChange(name, price);
    }

    private static void sendFullProductListOption() throws IOException {
        warehouseService.sendFullProductList(Lists.newArrayList(products.values()));
    }

    public static void main(String[] args) throws Exception {

        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            drawOptions();
            in = bufferedReader.readLine();
            if (NumberUtils.isNumber(in)) {
                int option = Integer.valueOf(in);
                switch(option){
                    case 1: addProductOption();
                        break;
                    case 2: removeProductOption();
                        break;
                    case 3: changePriceOption();
                        break;
                    case 4: sendFullProductListOption();
                        break;
                }
            }
        }


    }
}
