package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import java.util.*;
import au.com.bytecode.opencsv.*;s

public class CSVParser {
    //Student s = new Student("", "", "", "");
    //Course c = new Course("", "", "", "");
	private List<Student> students;
	private List<Course> courses;
	
	public CSVParser() {
		students = new ArrayList<>();
		courses = new ArrayList<>();
		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/students.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				students.add();
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
		
		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/courses.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Course c = new Course(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				courses.add();
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
    public void studentParser() throws Exception{
        CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/students.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0].replaceAll("\"", ""));
        }
    }

    public void courseParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0].replaceAll("\"", ""));
        }
    }

    public Student getStudent(String studentId) {
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(studentId.equals(s.getId())) return s;
		}
		return null;
    }
	
    public String courseLine(String courseId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
		String courseInfo = "";
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].equals('"'+courseId+'"')) {
               courseInfo =  "["+nextLine[0].replaceAll("\"", "")+"]"
                +" "+nextLine[1].replaceAll("\"", "")
                +" "+nextLine[2].replaceAll("\"", "")
                +" "+"("+nextLine[3].replaceAll("\"", "")+" students)";
				return courseInfo;
            }
            else {}
            //else {System.out.println("Course not found.");}
        }
		return "";
    }
}
