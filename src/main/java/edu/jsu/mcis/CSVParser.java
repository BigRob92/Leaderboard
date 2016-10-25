package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class CSVParser {

	private List<Student> students;
	private List<Course> courses;
	private List<String> studentIds;
	private List<String> courseIds;
	//private File f;
	//public List<File> files;

	public CSVParser() {
		students = new ArrayList<>();
		studentIds = new ArrayList<>();
		courses = new ArrayList<>();
		courseIds = new ArrayList<>();
		//f = new File("C:/Users/Cody/Desktop/Leaderboard/src/main/resources/courses");
		//files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/Cody/Desktop/Leaderboard/src/main/resources/students.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				students.add(s);
				studentIds.add(nextLine[0]);
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}

		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/Cody/Desktop/Leaderboard/src/main/resources/courses.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Course c = new Course(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				courses.add(c);
				courseIds.add(nextLine[0]);
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}

	public String getStudentIds() {
		return String.join("\n", studentIds);
	}

	public String getCourseIds() {
		return String.join("\n", courseIds);
	}

	public Student getStudent(String studentId) {
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(studentId.equals(s.getId())) return s;
		}
		return null;
    }
	
    public Course getCourse(String courseId) {
		for(int i = 0; i < courses.size(); i++){
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c;
		}
		return null;
    }
	//this returns each file directory in an array list as a string
	/*public List <File> getCourses(){
		return files;
	}*/
	
	
}
