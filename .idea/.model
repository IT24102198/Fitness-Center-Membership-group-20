package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Payment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    public static final String PATHNAME = "src/main/resources/payment.json";

    /**
     * Get by customer id
     *
     * @param id int
     * @return Payment
     */
    public Payment getByCustomerId(int id) {
        return getPayments().stream().filter(c -> c.getCustomerId() == id).findFirst().orElse(null);
    }

    /**
     * Get payments
     *
     * @return List of Payment
     */
    public List<Payment> getPayments() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(PATHNAME), new TypeReference<List<Payment>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Save new payment
     *
     * @param payment Payment
     */
    public void saveNewPayment(Payment payment) {
        try {
            payment.setPaymentDate(new Date());
            setAmount(payment);
            ObjectMapper mapper = new ObjectMapper();
            List<Payment> payments = getPayments();
            clearOldPayments(payments, payment.getCustomerId());
            payments.add(payment);
            mapper.writeValue(new File(PATHNAME), payments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear old payments
     *
     * @param payments List Of Payment
     * @param customerId int
     */
    private void clearOldPayments(List<Payment> payments, int customerId) {
        payments.removeIf(payment -> payment.getCustomerId() == customerId);
    }

    /**
     * Set amount
     *
     * @param payment Payment
     */
    private void setAmount(Payment payment) {
        switch (payment.getMembershipYears()) {
            case 1:
                payment.setAmount(1000.00);
                break;
            case 2:
                payment.setAmount(2000.00);
                break;
            case 3:
                payment.setAmount(3000.00);
                break;
        }
    }
}
