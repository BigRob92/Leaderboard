package edu.jsu.mcis;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class GamegogyGUITest{
	
	private GamegogyGUI g;
	
	@Before
	public void setUp(){
		g = new GamegogyGUI();
	}
	
	@Test
	public void testThatStudentNamesAreReturned(){
		List <String> tempList = g.getStudentNames();
		assertEquals(g.getStudentNames(), tempList);
	}
	
	@Test
	public void testThatStudentEmailsAreReturned(){
		List<String> tempList = g.getStudentEmails();
		assertEquals(g.getStudentEmails(), tempList);
	}
}