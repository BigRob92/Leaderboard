package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;


public class CSVParserTest {
	
	private CSVParser p;
    
	@Before
    public void setup() {
        p = new CSVParser();
    }
	
	@Test
	public void testThatCourseIdsAreLoaded() {		
		assertEquals("["+p.getCourseIds().toString()+"]", p.courseIds.toString());
	}
	
	@Test
	public void testReturnStudentIds() {
		assertEquals("["+p.getStudentIds()+"]", p.studentIds.toString());
	}

    @Test
    public void testParserReadsStudentInfo(){
        Student s = new Student("111111", "Jerrod", "Shields", "jshields");
		Student t = p.getStudent("111111");
        assertEquals(s, t);
    }
	
	@Test
	public void testParserReadsCourseInfo(){
		Course c = new Course("99018", "Spring", "2014", "16");
		Course t = p.getCourse("99018");
		assertEquals(c, t);
	}
	
}
