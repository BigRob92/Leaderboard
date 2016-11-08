package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;


public class CSVParserTest {
	
	private CSVParser p;
    
	@Before
    public void setup() {
        p = new CSVParser("111381");
    }
	
	@Test
	public void testThatFirstCourseIdIsCorrect() {
		assertEquals(p.getCourseIdsAsList().get(0),"99000");
	}

	@Test
	public void testThatLastCourseIdIsCorrect() {
		assertEquals(p.getCourseIdsAsList().get(24), "99024");
	}

	@Test
	public void testThatCourseListLengthIsCorrect() {
		assertEquals(p.getCourseIdsAsList().size(), 25);
	}
	
	@Test
	public void testThatFirstStudentIdIsCorrect(){
		assertEquals(p.getStudentIdsAsList().get(0), "111111");
	}
	
	@Test
	public void testThatLastStudentIdIsCorrect(){
		assertEquals(p.getStudentIdsAsList().get(299), "111410");
	}
	
	@Test
	public void testThatStudentListLengthIsCorrect(){
		assertEquals(p.getStudentIdsAsList().size(), 300);
	}

    @Test
    public void testParserReadsStudentInfo(){
        Student s = new Student("111111", "Jerrod", "Shields", "jshields");
		Student t = p.getStudent("111111");
        assertEquals(s, t);
    }
	@Test
	public void testThatStudentIsNotReturned(){
		Student t = p.getStudent("1");
		assertNull(t);
	}
	
	@Test 
	   public void testThatCourseIsNotReturned(){
		Course t = p.getCourse("1");
		assertNull(t);
	}
	
	@Test
	public void testParserReadsCourseInfo(){
		Course c = new Course("99018", "Spring", "2014", "16");
		Course t = p.getCourse("99018");
		assertEquals(c, t);
	}
	
	@Test
	public void testThatCourseEnrollmentIsGot(){
		assertEquals(p.getEnrollment("99018"), "16");
	}
	
	@Test
	public void testThatCourseTermIsReturned(){
		assertEquals(p.getCourseTerm("99018"), "Spring 2014");
	}
	
	@Test
	public void testThatStudentNameIsReturned(){
		assertEquals(p.getStudentName(), "Rex Gilliam");
	}
	
	@Test
	public void testThatStudentEmailIsReturned(){
		assertEquals(p.getStudentEmail(), "rgilliam@jsu.edu");
	}
	
}
