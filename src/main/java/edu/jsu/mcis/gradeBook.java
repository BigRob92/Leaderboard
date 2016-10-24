package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class gradeBook{


	private String term;
	
	public void setTerm(){
		term = "Fall 2016";
	}
	public String getTerm(){
		return term;
	}
	
	public int numberOfEnrolledStudents(String filePath){
		List <String> studentsEnrolled = new ArrayList<>();
		int enrollment;
		try{
			CSVReader reader = new CSVReader(new FileReader(filePath), ',', '\"', 1);
			String [] nextLine;
			while((nextLine = reader.readNext()) != null){
				studentsEnrolled.add(nextLine[0]);
			}
			enrollment = studentsEnrolled.size();
			return enrollment;
		}
		catch(IOException e){}
		return 0;
	}
	
	public List<String> getStudentsInCourse(String filepath){
		List <String> studentsEnrolled = new ArrayList<>();
		try{
			CSVReader reader = new CSVReader(new FileReader(filepath), ',', '\"', 1);
			String [] nextLine;
			while((nextLine = reader.readNext()) != null){
				studentsEnrolled.add(nextLine[0]);
			}
			return studentEnrolled;
		}
		catch(IOException e){}
		//catch(FileNotFoundException fnfe){}
		return null;
	}


}