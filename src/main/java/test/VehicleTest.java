package test;

import org.junit.Before;
import org.junit.Test;
import shadyAuto.Models.Vehicle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class VehicleTest {
    private Vehicle vehicle;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        String vehicleID = "testID123";
        String ownerID = "testOwnerID123";
        vehicle = new Vehicle(vehicleID, ownerID,"Toyota", "Camry", 2010, "4T1BFA");
        System.setOut(new PrintStream(outContent));
    }

    // Test parameterized constructor
    @Test
    public void testVehicleParameterizedConstructor() {
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Camry", vehicle.getModel());
        assertEquals(2010, vehicle.getYear());
        assertEquals("4T1BFA", vehicle.getLicensePlate());
        assertEquals("testID123", vehicle.getVehicleID());
        assertEquals("testOwnerID123", vehicle.getOwnerID());
    }


}
