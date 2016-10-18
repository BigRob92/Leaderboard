package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void testToString() {
		Course c = new Course("99018", "Spring", "2014", "16");
        assertEquals("[99018] Spring 2014 (16 students)", c.toString());
    }
    
	@Test
	public void testEquals() {
		Course c = new Course("99018", "Spring", "2014", "16");
		Course d = new Course("99018", "Spring", "2014", "16");
        assertEquals(c, d);
    }
}