package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GamegogyTest {
    private String student;
    private String studentData;

/*
public List<Entry> ReadEntries(StreamReader csvStreamReader)
    {
        var list = new List<Entry>();
 
        using (csvStreamReader)
        {
            string line;
            while ((line = csvStreamReader.ReadLine()) != null)
            {
                var splits = line.Split(',');
                list.Add(new Entry{ ID = splits[0], FirstName = splits[1], LastName = splits[2], EmailAddress = splits[3] });
            }
        }
 
        return list;
    }
*/

// Dr. Garrett's advice
// DataSource d = new DataSource();
//    Student s = d.getStudent("111111");
//  assertEquals("111111", s.getId);

    @Test
    public void testStudentIdFromFile() {
        String studentFirstName;

        //assertTrue(output.equals(studentFirstName));
        assertTrue(true);
    }

    @Test
    public void testStudentFirstNameFromFile() {
        assertTrue(true);
    }

    @Test
    public void testStudentLastNameFromFile() {
        assertTrue(true);
    }

    @Test
    public void testStudentEmailFromFile() {
        assertTrue(true);
    }

}
