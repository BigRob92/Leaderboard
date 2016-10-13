package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import au.com.bytecode.opencsv.*;

public class CSVParser {
    public void studentParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("students.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
        }
    }

    public void courseParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("courses.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
        }
    }

}