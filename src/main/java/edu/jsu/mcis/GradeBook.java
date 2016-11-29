package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class GradeBook{

	private List<String> ids;
	private List<String> columnHeaders;
	private List<Float>columnGrades;
	
	public GradeBook(String filePath, int column) {
		ids = new ArrayList<>();
		columnHeaders = new ArrayList<>();
		columnGrades = new ArrayList<>();
		try{
			InputStream resource = getClass().getClassLoader().getResourceAsStream(filePath);
			CSVReader reader = new CSVReader(new InputStreamReader(resource), ',','\"');
			String [] nextLine = reader.readNext();
			for (int i=1; i < nextLine.length; i++) {
				columnHeaders.add(nextLine[i]);
			}
			while((nextLine = reader.readNext()) != null){
				ids.add(nextLine[0]);
				columnGrades.add(Float.parseFloat(nextLine[column]));
			}
		}
		catch(IOException e){}
	}
	
	// We might use this for something
	public String getSingleId(int index) {
		return ids.get(index);
	}
	// This function returns a single grade.
	// The integer "row" correlates to the index
	// of the student id we are looking at.
	public Float getSingleGrade(int row) {
		sortColumnGrades();
		return columnGrades.get(row);
	}

	public List<Float> getColumnGrades(){
		sortColumnGrades();
		return columnGrades;
	}
	
	public void sortColumnGrades(){
		Comparator<Float> comparator = Collections.reverseOrder();
		Collections.sort(columnGrades,comparator);
	}
	
	public List<String> getColumnHeaders() {
		return columnHeaders;
	}
	

	public List<String> getIds(){
		return ids;
	}

	public String getEnrollment() {
		int enrollment = getIds().size();
		return Integer.toString(enrollment);
	}

}
