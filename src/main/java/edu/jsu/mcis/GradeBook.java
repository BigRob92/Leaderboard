// Fixed getHighestGrade() by making the function return
// a float instead of a string. Sorting the values as
// strings means 1xxxx < 2xxxx < ... < 9.
// Also made corresponding changes to GradeBookTest.java and
// now that test passes. ~Ben

package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class GradeBook{

	private List<String> ids;
	private List<String> columnHeaders;
	private List<List<Float>> grades;
	private List<Float> rowGrades;
	private List<Float> columnGrades;
	
	public GradeBook(String filePath) {
		ids = new ArrayList<>();
		columnHeaders = new ArrayList<>();
		grades = new ArrayList<>();
		rowGrades = new ArrayList<>();
		try{
			InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
			CSVReader reader = new CSVReader(new InputStreamReader(resource), ',','\"');
			//CSVReader reader = new CSVReader(new FileReader(filePath), ',','\"');
			String [] nextLine = reader.readNext();
			
			for (int i=1; i < nextLine.length; i++) {
				columnHeaders.add(nextLine[i]);
			}

			while((nextLine = reader.readNext()) != null){
				ids.add(nextLine[0]);
				for (int i=1; i < nextLine.length; i++) {
					rowGrades.add(Float.parseFloat(nextLine[i]));
					grades.add(rowGrades);
				}
			}
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
	
	//returns the HighestGrade. Just pass in the file you wish to search and the column of that file you wish to search.
	public List<Float> getColumnGrades(String filePath, int column) {
		columnGrades = new ArrayList<>();
		try {
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/"+filePath+".csv") , ',', '\"');
			String nextLine [] = reader.readNext();
			while ((nextLine = reader.readNext()) !=null){
				columnGrades.add(Float.parseFloat(nextLine[column]));
			}
			//int highestGrade = Collections.max(columnGrades);
			//return highestGrade;
			return columnGrades;
		}
		catch(IOException ioe){}
		return null;
		//while there is more to read
		//	add the column you wish to search into the arrayList.
		//	find the max value in that column and sets it equal to a variable;
		// 	returns a single grade
	}
	
	public List<String> getColumnHeaders() {
		return columnHeaders;
	}

	public List<List<Float>> getGrades(){
		return grades;
	}
	
	public List<Float> getRowGrades() {
		return rowGrades;
	}

	public List<String> getIds(){
		return ids;
	}
	public String getIdsAsString() {
		return String.join("/n", ids);
	}

	public String getEnrollment() {
		int enrollment = getIds().size();
		return Integer.toString(enrollment);
	}

}
