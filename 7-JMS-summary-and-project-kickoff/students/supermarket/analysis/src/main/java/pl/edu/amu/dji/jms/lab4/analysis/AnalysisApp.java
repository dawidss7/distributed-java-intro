package pl.edu.amu.dji.jms.lab4.analysis;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.analysis.report.ReportService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnalysisApp {
    public static final String EXIT = "exit";
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static ReportService reportService = (ReportService) context.getBean("reportService");

    private static void drawOptions() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("OPTIONS");
        System.out.println("1. CHECK REPORT;");
    }
    private static void checkReportOption() throws IOException {
        System.out.println("SOLD PRODUCTS: ");
        reportService.getSalesInformations().forEach((salesInformation)-> System.out.println(salesInformation.getName()+ " " + salesInformation.getPrice()+"$"));
    }


    public static void main(String[] args) throws Exception {

        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            drawOptions();
            in = bufferedReader.readLine();
            if (NumberUtils.isNumber(in)) {
                int option = Integer.valueOf(in);
                switch(option){
                    case 1: checkReportOption();
                        break;
                }
            }
        }
    }
}
