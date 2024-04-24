package test;

import org.junit.Before;
import org.junit.Test;
import shadyAuto.Models.Part;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class PartsTest {
    private Part part;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        part = new Part("testPartID", "testPart", 100.00);
        System.setOut(new PrintStream(outContent));
    }

    // Test parameterized constructor
    @Test
    public void testPartParameterizedConstructor() {
        assertEquals("testPartID", part.getPartID());
        assertEquals("testPart", part.getName());
        assertEquals(100.00, part.getPrice(), 0.01);
    }

}