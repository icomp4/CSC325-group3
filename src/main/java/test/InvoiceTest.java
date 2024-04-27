package test;

import org.junit.Before;
import org.junit.Test;
import shadyAuto.Models.Invoice;
import shadyAuto.Models.Part;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InvoiceTest {
    private Invoice invoice;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        String invoiceID = "testID123";
        String vehicleID = "testVehicleID123";
        String customerID = "testCustomerID123";

        ArrayList<Part> partsOrder = new ArrayList<>();
        String partID = "testPartID";
        Part part = new Part(partID,"testPart", 100.00);
        partsOrder.add(part);
        String PartID2 = "testPartID2";
        Part part2 = new Part(PartID2,"testPart2", 200.00);
        partsOrder.add(part2);
        String PartID3 = "testPartID3";
        Part part3 = new Part(PartID3,"testPart3", 300.00);
        partsOrder.add(part3);

        invoice = new Invoice(invoiceID, vehicleID, customerID, partsOrder, "2021-04-01");
        System.setOut(new PrintStream(outContent));
    }

    // Test parameterized constructor
    @Test
    public void testInvoiceParameterizedConstructor() {
        assertEquals("testID123", invoice.getInvoiceID());
        assertEquals("testVehicleID123", invoice.getVehicleID());
        assertEquals("testCustomerID123", invoice.getCustomerID());
        assertEquals(600.00, invoice.getPrice(), 0.01);
        assertEquals("2021-04-01", invoice.getDate());
    }


}
