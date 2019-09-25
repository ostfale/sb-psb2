package de.ostfale.todojdbc2.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Tests repository functions
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createSimpleCustomer() {
        Customer customer = new Customer();
        customer.dob = LocalDate.of(1907, 5, 12);
        customer.firstName = "Franz";

        Customer saved = customerRepository.save(customer);
        assertNotNull(saved.id);

        saved.firstName = "Hans Albers";
        customerRepository.save(saved);

        Optional<Customer> reloaded = customerRepository.findById(saved.id);
        assertTrue(reloaded.isPresent());
        assertEquals("Hans Albers", reloaded.get().firstName);
    }

    @Test
    public void findByName() {
        Customer customer = new Customer();
        customer.dob = LocalDate.of(1907, 5, 12);
        customer.firstName = "Albert";

        Customer saved = customerRepository.save(customer);

        assertNotNull(saved.id);

        /*
         * Since the connection between a Java object and its corresponding row is
         * just its Id plus its type, setting the Id to null and saving it again
         * creates another row in the database.
         */
        customer.id = null;
        customer.firstName = "Bert";
        customerRepository.save(customer);

        customer.id = null;
        customer.firstName = "Xanthippe";

        customerRepository.save(customer);

        assertEquals(2, customerRepository.findByName("bert").size());
    }
}