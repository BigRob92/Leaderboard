package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class CSVParser {

	private List<Student> students;
	private List<Course> courses;
	private List<String> studentIds;
	private List<String> courseIds;

	public CSVParser() {
		students = new ArrayList<>();
		studentIds = new ArrayList<>();
		courses = new ArrayList<>();
		courseIds = new ArrayList<>();

		try {
			CSVReader reader = new CSVReader(new FileReader("src/main/java/resources/students.csv"), ',', '\"', 1);
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
			CSVReader reader = new CSVReader(new FileReader("src/main/java/resources/courses.csv"), ',', '\"', 1);
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
			List<String> output = new ArrayList<String>(studentIds);
			String csv = String.join("\n", output);
			return csv;
		}

		public String getCourseIds() {
			List<String> output = new ArrayList<String>(courseIds);
			String csv = String.join("\n", output);
			return csv;
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

}
