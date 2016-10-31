package edu.jsu.mcis;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class GradeBookTest{
	
	private GradeBook gb;

	@Before
	public void setUp(){
		gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99001.csv");
	}
	
	public void testThatEnrollmentIsReturned(){
	assertEquals(gb.getEnrollment(),"11");
	}
	
	@Test
	public void testThatHighestGradeIsReturned(){
		assertTrue(gb.getHighestGrade("99001", 2) == 124);
	}
	
	@Test
	public void seeWhatsInColumnGrades(){
		gb.getHighestGrade("99001", 2);
		assertEquals(gb.getColumnGrades(), gb.getColumnHeaders());
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