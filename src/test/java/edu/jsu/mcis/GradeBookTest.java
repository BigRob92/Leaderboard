package edu.jsu.mcis;
import org.junit.*;
import java.util.*;
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
		List<String> studentList = Arrays.asList("111318", "111383", "111190", "111406", "111115", "111211", "111208", "111310", "111335", "111141", "111262");
		assertEquals(gb.getIds(), studentList);
	}
	
	@Test
	public void testThatGradesAreReturned(){
		assertEquals(gb.getGrades().size(), gb.getIds().size()*gb.getColumnHeaders().size());
	}
	
}