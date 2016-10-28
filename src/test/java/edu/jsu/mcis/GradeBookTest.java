package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;

public class GradeBookTest{
	
	private GradeBook gb;
	private CSVParser p;

	@Before
	public void setUp(){
		gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv");
		p = new CSVParser();
	}
	
		public void testThatEnrollmentIsReturned(){
		assertEquals(gb.getEnrollment(),"11");
	}
	
	@Test
	public void testThatStudentsInCourseAreReturned(){
		assertNotNull(gb.getIds());
	}
	
	@Test
	public void testThatGradesAreReturned(){
		assertEquals(gb.getGrades().size(), gb.getIds().size()*gb.getColumnHeaders().size());
	}
	
	

}