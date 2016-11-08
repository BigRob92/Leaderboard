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
	
	public List<Float> getColumnGrades(){
		return columnGrades;
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
