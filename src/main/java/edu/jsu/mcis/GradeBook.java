package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class GradeBook{
	private List<String> ids;
	private List<String> columnHeaders;
	private List<List<Float>> grades;
	
	
	//probably wont like this since we have to call this constructor every time we want to add grades to the list.
	public GradeBook(String filePath) {
		ids = new ArrayList<>();
		columnHeaders = new ArrayList<>();
		grades = new ArrayList<>();
		try{
			CSVReader reader = new CSVReader(new FileReader(filePath), ',','\"');
			Float [] nextGrade;
			String [] nextLine = reader.readNext();
			List<Float> rowGrades = new ArrayList<>();
			
			for (int i=1; i < nextLine.length; i++) {
				columnHeaders.add(nextLine[i]);
			}

			while((nextLine = reader.readNext()) != null){
				ids.add(nextLine[0]);
				for (int i=1; i < nextLine.length; i++) {
					rowGrades.add(Float.parseFloat(nextLine[i]));
					//grades.add(rowGrades);
				}
			}
			grades.add(rowGrades);
		}
		catch(IOException e){}
		// load the CSV file for processing
		// read the first row and put everything but ID in the column headers
		// while there is more to read
		//    add the ID to the ids list
		//    make a new List<Float> to hold the grades for this row
		//    add each grade in the row to that list
		//    add that list to the grades list (of lists)

	}
	
	public List<String> getColumnHeaders() {
		return columnHeaders;
	}

	public List<List<Float>> getGrades(){
		return grades;
	}
	
	public List <String> getIds(){
		return ids;
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
	
	
	
	//this already returns the student Ids and puts them in a list.
	public List<String> getStudentsInCourse(String filepath){
		List <String> studentsEnrolled = new ArrayList<>();
		try{
			CSVReader reader = new CSVReader(new FileReader(filepath), ',', '\"', 1);
			String [] nextLine;
			while((nextLine = reader.readNext()) != null){
				studentsEnrolled.add(nextLine[0]);
			}
			return studentsEnrolled;
		}
		catch(IOException e){}
		//catch(FileNotFoundException fnfe){}
		return null;
	}


}
