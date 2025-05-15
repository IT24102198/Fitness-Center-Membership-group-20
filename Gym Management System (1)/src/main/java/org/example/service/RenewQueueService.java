package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Customer;
import org.example.model.RenewQueue;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RenewQueueService {

    private final String PATHNAME = "src/main/resources/renewqueue.json";

    private Calendar cal = Calendar.getInstance();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Get by customer id
     *
     * @param id int
     * @return Payment
     */
    public RenewQueue getByCustomerId(int id) {
        return getRenewQueue().stream().filter(c -> c.getCustomerId() == id).findFirst().orElse(null);
    }

    public List<RenewQueue> getSortedRenewQueue() {
        List<RenewQueue> list = getRenewQueue();
        for (int i = 1; i < list.size(); i++) {
            RenewQueue key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getRenewalDate().after(key.getRenewalDate())) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    private List<RenewQueue> getRenewQueue() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(PATHNAME), new TypeReference<List<RenewQueue>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveRenewQueue(List<Customer> customers) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<RenewQueue> list = new ArrayList<>();
            Date today = new Date();
            cal.add(Calendar.DATE, 10);
            Date renewalDate = cal.getTime();
            for (Customer customer : customers) {
                if (customer.getMembershipEndDate().before(renewalDate)) {
                    list.add(getRenewObject(customer, today));
                }
            }
            mapper.writeValue(new File(PATHNAME), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RenewQueue getRenewObject(Customer customer, Date today) {
        RenewQueue renewQueue = new RenewQueue();
        renewQueue.setCustomerId(customer.getRegisterNo());
        renewQueue.setCustomerName(customer.getName());
        cal.setTime(customer.getMembershipEndDate());
        cal.add(Calendar.DATE, 1);
        renewQueue.setRenewalDate(cal.getTime());
        renewQueue.setQueuedDate(sdf.format(today));
        return renewQueue;
    }
}