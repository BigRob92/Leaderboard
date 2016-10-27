package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;

public class GradeBookTest{
	
	private GradeBook gb;
	
	@Before
	public void setUp(){
		gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv");
	}
	
	@Test
	public void testThatEnrollmentIsReturned(){
		assertEquals(gb.numberOfEnrolledStudents("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv"),11);
	}
	
	@Test
	public void testThatStudentsInCourseAreReturned(){
		assertNotNull(gb.getIds());
	}
	
	@Test
	public void testThatGradesAreReturned(){
		assertEquals(gb.getGrades(), gb.getStudentsInCourse("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv"));
	}
	
	

}