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
	}
	
	public List<Float> getColumnGrades(String filePath, int column) {
		columnGrades = new ArrayList<>();
		try {
			InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
			CSVReader reader = new CSVReader(new InputStreamReader(resource), ',', '\"');
			String nextLine [] = reader.readNext();
			while ((nextLine = reader.readNext()) !=null){
				columnGrades.add(Float.parseFloat(nextLine[column]));
			}
			return columnGrades;
		}
		catch(IOException ioe){}
		return null;
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
