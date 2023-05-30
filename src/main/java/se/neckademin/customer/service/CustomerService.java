package se.neckademin.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.neckademin.customer.Model.Customer;
import se.neckademin.customer.exception.NotSavedCustomerException;
import se.neckademin.customer.repository.CustomerRepo;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    private CustomerRepo dao;

    @Autowired
    public CustomerService(CustomerRepo dao) {
        this.dao = dao;
    }

    public List<Customer> findAllCustomer() {
        List<Customer> allCustomer = dao.findAll();
        if (allCustomer.isEmpty()) {
            log.info("No Customer found!");
        }
        return allCustomer;
    }

    public Boolean saveCustomer(Customer newCustomer) throws NotSavedCustomerException {
        Customer customer = dao.save(newCustomer);
        if (customer.equals(newCustomer)) {
            return true;
        } else {
            throw new NotSavedCustomerException(String.format("there is not customer with id = %s", customer.getCustomerId()));
        }
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> customerById = dao.findById(id);
        if (customerById.isPresent()) {
            log.info("customer by id={} ", id);
            return customerById.get();
        } else {
            log.info("No Customer found by id={}", id);
            return null;
        }
    }

}