package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class GradeBook{

	private List<String> ids;
	private List<String> columnHeaders;
	private List<List<Float>> grades;
	
	public GradeBook(String filePath) {
		ids = new ArrayList<>();
		columnHeaders = new ArrayList<>();
		grades = new ArrayList<>();
		List <Float>rowGrades = new ArrayList<>();
		try{
			InputStream resource = getClass().getClassLoader().getResourceAsStream(filePath);
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
	
	public List<Float> getColumnGrades(int column) {
		List <Float> columnGrades = new ArrayList<>();
		for(List<Float> row : grades) {
			columnGrades.add(row.get(column));
		}
		return columnGrades;
	}
	
	public List<String> getColumnHeaders() {
		return columnHeaders;
	}

	public List<List<Float>> getGrades(){
		return grades;
	}
	
	public List<Float> getRowGrades(int row) {
		return grades.get(row);
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
