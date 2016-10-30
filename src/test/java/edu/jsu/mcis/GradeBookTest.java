package edu.jsu.mcis;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class GradeBookTest{
	
	private GradeBook gb;

	@Before
	public void setUp(){
		gb = new GradeBook("C:/Users/skate/Desktop/Leaderboard/src/main/resources/courses/99001.csv");
	}
	
	public void testThatEnrollmentIsReturned(){
	assertEquals(gb.getEnrollment(),"11");
	}
	
	
	//Should return 124 but it returns 98. I'll ask Dr. Garret tomorrow what is wrong with it. The max function is not working properly.
	@Test
	public void testThatHighestGradeIsReturned(){
		assertEquals(gb.getHighestGrade("99001", 2), "124");
	}
	
	@Test
	public void seeWhatsInColumnGrades(){
		gb.getHighestGrade("99001", 2);
		assertEquals(gb.getColumnGrades(), gb.getColumnHeaders());
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

//add test to get one particular grade
//use that method in core class(es)