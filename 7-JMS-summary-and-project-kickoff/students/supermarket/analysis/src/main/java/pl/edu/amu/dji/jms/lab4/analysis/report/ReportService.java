package pl.edu.amu.dji.jms.lab4.analysis.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.message.FullProductList;
import pl.edu.amu.dji.jms.lab4.message.SalesInformation;

import javax.jms.Destination;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 10.12.14.
 */
@Service("reportService")
public class ReportService {
    private List<SalesInformation> salesInformations = new LinkedList<>();

    public List<SalesInformation> getSalesInformations() {
        return salesInformations;
    }

    public void setSalesInformations(List<SalesInformation> salesInformations) {
        this.salesInformations = salesInformations;
    }

    @Transactional
    public void addSalesInformation(final SalesInformation salesInformation) {
        salesInformations.add(salesInformation);
    }

}
