package se.neckademin.customer.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.neckademin.customer.Model.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);
    List<Customer> findBySsn(String ssn);
    List<Customer> findAll();

    @Transactional
    void deleteAllByName(String name);
    @Transactional
    void deleteAllBySsn(String ssn);

}
