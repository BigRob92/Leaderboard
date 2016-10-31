package edu.jsu.mcis;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class GradeBookTest{
	
	private GradeBook gb;

	@Before
	public void setUp(){
		gb = new GradeBook("C:/Users/skate/Desktop/Leaderboard-master/Leaderboard-master/src/main/resources/courses/99001.csv");
	}
	
	@Test
	public void testThatEnrollmentIsReturned(){
	assertEquals(gb.getEnrollment(),"9");
	}
	
	@Test
	public void testThatHighestGradeIsReturned(){
		assertEquals(gb.getHighestGrade("99001", 2),132);
	}
	
	@Test
	public void testThatStudentsInCourseAreReturned(){
		List<String> studentList = Arrays.asList("111291", "111208", "111148", "111236", "111326", "111293", "111143", "111157", "111254");
		assertEquals(gb.getIds(), studentList);
	}
	
	@Test
	public void testThatGradesAreReturned(){
		assertEquals(gb.getGrades().size(), gb.getIds().size()*gb.getColumnHeaders().size());
	}
	
}

//add test to get one particular grade
//use that method in core class(es)