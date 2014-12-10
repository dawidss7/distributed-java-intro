package pl.edu.amu.dji.jms.lab4;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.pointOfSales.PointOfSalesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PointOfSalesApp {
    public static final String EXIT = "exit";
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static PointOfSalesService pointOfSalesService = (PointOfSalesService) context.getBean("pointOfSalesService");

    private static void drawOptions() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("AVAILABLE PRODUCTS");
        pointOfSalesService.getProducts().values().forEach((product) -> System.out.println(product.getName() + " " + product.getPrice() + "$"));
        System.out.println("---------------------------------");
        System.out.println("OPTIONS");
        System.out.println("1. BUY PRODUCT; 2. REFRESH PRODUCT LIST;");

    }

    private static void buyProductOption() throws IOException {
        System.out.print("Enter product name: ");
        String name = bufferedReader.readLine();
        Product product = pointOfSalesService.getProducts().get(name);
        if (product == null) {
            return;
        }
        pointOfSalesService.sendSalesInformation(product);
    }


    public static void main(String[] args) throws Exception {

        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            drawOptions();
            in = bufferedReader.readLine();
            if (NumberUtils.isNumber(in)) {
                int option = Integer.valueOf(in);
                switch (option) {
                    case 1:
                        buyProductOption();
                        break;
                }
            }
        }
    }
}
