package de.fred4jupiter.springboot.thymeleaf.data;

import de.fred4jupiter.springboot.thymeleaf.customer.Customer;
import de.fred4jupiter.springboot.thymeleaf.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataPopulator {

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void initDatabase() {
        if (customerRepository.count() > 0) {
            return;
        }

        List<Customer> initialCustomers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setName(UUID.randomUUID().toString());
            initialCustomers.add(customer);
        }
        customerRepository.saveAll(initialCustomers);
    }
}
