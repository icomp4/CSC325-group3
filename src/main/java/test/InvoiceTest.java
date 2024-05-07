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
        assertEquals("600.0", String.valueOf(invoice.getPrice()));
        assertEquals("2021-04-01", invoice.getDate());
    }
    @Test
    public void testCustomGetters() {
        Invoice invoice2 = new Invoice("028b516b-ef11-4f33-8be5-6219fe7c5402", "testVehicleID123", "testCustomerID123", new ArrayList<Part>(), "2021-04-01");
        assertEquals("nuna mako", invoice2.getOwnerName()); // calls the firebase database to retrieve the full name of the owner associated with the invoice
        assertEquals("2019 volkswagen beetle", invoice2.getVehicleDetails()); // calls the firebase database to retrieve the vehicle details associated with the invoice
    }


}
