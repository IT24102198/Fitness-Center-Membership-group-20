package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Report;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ReportService {

    private final String PATHNAME = "src/main/resources/report.json";

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    /**
     * Get by report id
     *
     * @param id int
     * @return Report
     */
    public Report getByCustomerId(int id) {
        return getReports().stream().filter(c -> c.getCustomer().getRegisterNo() == id).findFirst().orElse(null);
    }

    /**
     * Get reports
     *
     * @return List of Report
     */
    private List<Report> getReports() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(PATHNAME), new TypeReference<List<Report>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Save new report
     *
     * @param report Report
     */
    public void saveNewReport(Report report) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Report> reports = getReports();
            clearOldReports(reports, report.getCustomer().getRegisterNo());
            reports.add(report);
            mapper.writeValue(new File(PATHNAME), reports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear old report
     *
     * @param reports    List Of Report
     * @param customerId int
     */
    private void clearOldReports(List<Report> reports, int customerId) {
        reports.removeIf(report -> report.getCustomer().getRegisterNo() == customerId);
    }

    public String getHTMLForPDF(int id) {
        Report report = getByCustomerId(id);
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><style>body { font-family: sans-serif; }</style></head><body>");
        builder.append("<div class=\"overlay\"><h3 class=\"text-center\" >Report : ");
        builder.append(report.getCustomer().getName()).append("</h3>");
        builder.append("<div class=\"section\"><h4>Customer Details</h4><table class=\"details-table\">");
        appendValue(builder, "Register No", Integer.toString(report.getCustomer().getRegisterNo()));
        appendValue(builder, "Name", report.getCustomer().getName());
        appendValue(builder, "Age", Integer.toString(report.getCustomer().getAge()));
        appendValue(builder, "Address", report.getCustomer().getAddress());
        appendValue(builder, "Email", report.getCustomer().getEmail());
        appendValue(builder, "Mobile No", Integer.toString(report.getCustomer().getMobileNo()));
        appendValue(builder, "Weight", Integer.toString(report.getCustomer().getWeight()) + " kg");
        appendValue(builder, "Height", Integer.toString(report.getCustomer().getHeight()) + " cm");
        builder.append("</table></div>");
        builder.append("<div class=\"section\"><h4>Membership</h4><table class=\"details-table\">");
        appendValue(builder, "Membership Years", Integer.toString(report.getPayment().getMembershipYears()));
        appendValue(builder, "Membership Start Date", formatter.format(report.getCustomer().getMembershipStartDate()));
        appendValue(builder, "Membership End Date", formatter.format(report.getCustomer().getMembershipEndDate()));
        String formattedAmount = currencyFormatter.format(report.getPayment().getAmount());
        String customFormattedAmount = formattedAmount.replace("₹", "Rs");
        appendValue(builder, "Amount", customFormattedAmount);
        builder.append("</table></div>");
        builder.append(getWorkoutPlan());
        builder.append("</div></body></html>");
        return builder.toString();
    }

    private void appendValue(StringBuilder builder, String key, String value) {
        builder.append("<tr><td><strong>");
        builder.append(key);
        builder.append("</strong></td><td>");
        builder.append(value);
        builder.append("</td></tr>");
    }

    private String getWorkoutPlan() {
        return """
                <div class="section">
                                    <h4>Workout Plan</h4>
                                    <table class="order-details-table">
                                        <tr>
                                            <th></th>
                                            <th class="text-center">Week 1</th>
                                            <th class="text-center">Week 2</th>
                                            <th class="text-center">Week 3</th>
                                            <th class="text-center">Week 4</th>
                                        </tr>
                                        <tr>
                                            <td>Monday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Tuesday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Wednesday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Thursday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Friday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Saturday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Sunday</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                </div>""";
    }
}