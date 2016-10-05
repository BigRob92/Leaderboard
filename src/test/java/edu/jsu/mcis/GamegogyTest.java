package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class GamegogyTest {
    private String student;
    private String studentData;
    
    /*
    private static String readFile(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String out = "";

        try {
            while(scanner.hasNextLine()) {
                out += scanner.nextLine() + "\n";
            }
            return out;
        } finally {
            scanner.close();
        }
    }*/

/*    FileReader file = new FileReader(new File("data/students.csv"));
    BufferedReader reader = new BufferedReader(file); 
    String line;

    while ((line = reader.readLine()) != null) {
        System.out.println(line.split(" ")[0]);
    }
 */   
    //@Before
    /*public void setUp() {
        try {
            studentData = readFile("data/students.csv");
        } catch(IOException e) {}
    }*/

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
        assertTrue(false);
        // stuff
    }

    @Test
    public void testStudentFirstNameFromFile() {
        assertTrue(false);
    }

    @Test
    public void testStudentLastNameFromFile() {
        assertTrue(false);
    }

    @Test
    public void testStudentEmailFromFile() {
        assertTrue(false);
    }

}



//unit test goes here

