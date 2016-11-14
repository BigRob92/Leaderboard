package edu.jsu.mcis;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;


public class GradeBookTest{
	
	private GradeBook gb;

	@Before
	public void setUp(){
		gb = new GradeBook("courses/99001.csv", 2);
	}
	
	@Test
	public void testThatEnrollmentIsReturned(){
	assertEquals(gb.getEnrollment(),"9");
	}
	
	@Test
	public void testThatHighestGradeIsReturned(){
		List<Float> columnGrades = new ArrayList<>(gb.getColumnGrades());
		Float highGrade = Collections.max(columnGrades);
		assertEquals(highGrade , 132, 0.000000000001);
	}
	
	@Test
	public void testThatStudentsInCourseAreReturned(){
		List<String> studentList = Arrays.asList("111291", "111208", "111148", "111236", "111326", "111293", "111143", "111157", "111254");
		assertEquals(gb.getIds(), studentList);
	}
	
	@Test
	public void testThatColumnGradesAreSorted(){
		gb.sortColumnGrades();
		List <Float> columnGrades = new ArrayList<>(gb.getColumnGrades());
		assertEquals(gb.getColumnGrades(), columnGrades);
	}
	
}