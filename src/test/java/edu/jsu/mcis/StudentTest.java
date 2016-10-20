package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;


public class StudentTest {

    @Test
    public void testToString() {
		Student s = new Student("111128", "Maritza", "Abbott", "mabbott");
        assertEquals("[111128] Maritza Abbott mabbott@jsu.edu", s.toString());
    }
    
	@Test
	public void testEquals() {
		Student s = new Student("111128", "Maritza", "Abbott", "mabbott");
		Student t = new Student("111128", "Maritza", "Abbott", "mabbott");
		assertEquals(s, t);
	}
		
}
