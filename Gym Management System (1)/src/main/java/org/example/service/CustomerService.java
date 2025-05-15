package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Customer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    public static final String PATHNAME = "src/main/resources/customer.json";

    public List<Customer> getCustomers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(PATHNAME), new TypeReference<List<Customer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Customer> getUpdatedCustomers() {
        // Assuming this method fetches and potentially updates customer data
        return getCustomers(); // Modify this if there's specific update logic
    }

    public Customer getById(int id) {
        return getCustomers().stream().filter(c -> c.getRegisterNo() == id).findFirst().orElse(null);
    }

    public int getNextCustomerIdCounter() {
        List<Customer> customers = getCustomers();
        if (customers.isEmpty()) {
            return 1;
        }
        return customers.stream().mapToInt(Customer::getRegisterNo).max().orElse(0) + 1;
    }

    public void saveNewCustomer(Customer newCustomer) {
        List<Customer> customers = getCustomers();
        customers.add(newCustomer);
        saveCustomersToFile(customers);
    }

    private void saveCustomersToFile(List<Customer> customers) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(PATHNAME), customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMembership(int id, int membershipYears) {
        List<Customer> customers = getCustomers();
        customers.stream()
                .filter(c -> c.getRegisterNo() == id)
                .findFirst()
                .ifPresent(customer -> {
                    // Assuming you have membership start and end date fields
                    // You'll need to implement the logic to update these based on membershipYears
                    // This is a placeholder:
                    System.out.println("Membership updated for customer: " + customer.getName());
                });
        saveCustomersToFile(customers);
    }

    public Customer searchCustomerByName(String keyword) {
        List<Customer> customers = getCustomers();
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return customer;
            }
        }
        return null;
    }

    public void updateCustomer(Customer updatedCustomer) {
        List<Customer> customers = getCustomers();
        List<Customer> updatedList = customers.stream()
                .map(customer -> {
                    if (customer.getRegisterNo() == updatedCustomer.getRegisterNo()) {
                        return updatedCustomer;
                    }
                    return customer;
                })
                .collect(Collectors.toList());
        saveCustomersToFile(updatedList);
    }

    public void deleteCustomer(int id) {
        List<Customer> customers = getCustomers();
        List<Customer> updatedList = customers.stream()
                .filter(customer -> customer.getRegisterNo() != id)
                .collect(Collectors.toList());
        saveCustomersToFile(updatedList);
    }
}