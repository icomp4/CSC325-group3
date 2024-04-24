package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import shadyAuto.Models.Customer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

public class CustomerTest {
    private Customer customer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        customer = new Customer(UUID.randomUUID().toString(), "John", "Doe", "1234567890");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCustomerDefaultConstructor() {
        Customer newCustomer = new Customer();
        assertEquals("null", newCustomer.getFirstName());
        assertEquals("null", newCustomer.getLastName());
        assertNull(newCustomer.getPhoneNumber());
    }

    @Test
    public void testCustomerParameterizedConstructor() {
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertNotNull(customer.getCustomerID()); // Ensures a UUID is generated
    }
}