package edu.jsu.mcis;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class GamegogyGUI extends JFrame {
    
    private JLabel termLabel;
    private JLabel enrollmentLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel scoreLabel;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> columnComboBox;
    
    private CSVParser p;
    private GradeBook gb;
    private Student s;
    private Course c;
    private List<String> coursesList;
    private List<Float> columnGrades;
    private List<String> studentIds;

    private String courseSELECTED;
    private String courseId;
    private String topStudentId;

    private Float highGrade;
    private int gradeIndex;
    private int headerIndex;

    public GamegogyGUI() {
        initComponents();
    }

    public List<String> getCourseComboValues() {
        return p.getCourseIdsAsList();
    }

    public List<String> getColumnComboValues() {
        return gb.getColumnHeaders();
    }

    private void initComponents() {
        p = new CSVParser("111111");
        gb = new GradeBook("courses/99000.csv", 1);
        setTitle("Gamegogy");
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        termLabel = new JLabel(p.getCourseTerm("99000"));
		termLabel.setName("courseTerm");
        enrollmentLabel = new JLabel(p.getEnrollment("99000"));
		enrollmentLabel.setName("courseEnrollment");
        idLabel = new JLabel();
		idLabel.setName("studentId");
        nameLabel = new JLabel();
		nameLabel.setName("studentName");
        emailLabel = new JLabel();
		emailLabel.setName("studentEmail");
        scoreLabel = new JLabel();
		scoreLabel.setName("studentScore");
        panel.add(new JLabel("Term: ", JLabel.LEFT));
        panel.add(termLabel);
        panel.add(new JLabel("Enrollment: ", JLabel.LEFT));
        panel.add(enrollmentLabel);
        panel.add(new JLabel("ID: ", JLabel.LEFT));
        panel.add(idLabel);
        panel.add(new JLabel("Name: ", JLabel.LEFT));
        panel.add(nameLabel);
        panel.add(new JLabel("Email: ", JLabel.LEFT));
        panel.add(emailLabel);
        panel.add(new JLabel("Score: ", JLabel.LEFT));
        panel.add(scoreLabel);


        courseComboBox = new JComboBox<>(p.getCourseIdsAsList().toArray(new String[0]));
		courseComboBox.setName("courseComboBox");						
		idLabel.setText("111318");
		nameLabel.setText("Cathleen Guzman");
		emailLabel.setText("cguzman@jsu.edu");
		scoreLabel.setText("925.0");
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox courseComboBox = (JComboBox) event.getSource();
                Object courseSelected = courseComboBox.getSelectedItem();
				courseComboBox.setSelectedItem(courseSelected);
				courseSELECTED = courseSelected.toString();

				if(courseSELECTED != null && !courseSELECTED.isEmpty()) {
			
					gb = new GradeBook("courses/"+courseSELECTED+".csv", 1);
					columnComboBox.setModel(new DefaultComboBoxModel<>(gb.getColumnHeaders().toArray(new String[0])));
					setCourse(courseSELECTED);
					
					termLabel.setText(p.getCourseTerm(courseSELECTED));
					enrollmentLabel.setText(p.getEnrollment(courseSELECTED));
					columnGrades = new ArrayList<>(gb.getColumnGrades());
					highGrade = Collections.max(columnGrades);
					gradeIndex = columnGrades.indexOf(highGrade);
					studentIds = gb.getIds();
					topStudentId = studentIds.get(gradeIndex);
					p = new CSVParser(topStudentId);
					idLabel.setText(topStudentId);
					nameLabel.setText(p.getStudentName());
					emailLabel.setText(p.getStudentEmail());
					scoreLabel.setText(Float.toString(highGrade));
							
				} 
			}
		});
        
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
		columnComboBox.setName("columnComboBox");
        columnComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				try{
					JComboBox columnComboBox = (JComboBox) event.getSource();
					Object headerSelected = columnComboBox.getSelectedItem();
					List<String> headerList = getColumnComboValues();
					headerIndex = headerList.indexOf(headerSelected) + 1;
					courseSELECTED = getCourse();
					gb = new GradeBook("courses/"+courseSELECTED+".csv", headerIndex);
					List<List<Float>> gradesList = gb.getGrades();
					columnGrades = new ArrayList<>(gb.getColumnGrades());
					highGrade = Collections.max(columnGrades);
					gradeIndex = columnGrades.indexOf(highGrade);
					studentIds = gb.getIds();
					topStudentId = studentIds.get(gradeIndex);
					p = new CSVParser(topStudentId);
					idLabel.setText(topStudentId);
					scoreLabel.setText(Float.toString(highGrade));
					nameLabel.setText(p.getStudentName());
					emailLabel.setText(p.getStudentEmail());
				}
				catch(NullPointerException e){}
			}
		});
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        
        JLabel courseLabel = new JLabel("Course");
        JLabel columnLabel = new JLabel("Column");
        
        topPanel.add(courseComboBox, BorderLayout.WEST);
        topPanel.add(courseLabel);
        topPanel.add(columnComboBox, BorderLayout.EAST);
        //topPanel.add(columnLabel);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void setCourse(String courseSELECTED) {
        courseId = courseSELECTED;
    }

    public String getCourse() {
        return courseId;
    }

    public void changeCourse(ActionEvent event) {
        populateColumnComboBox();
    }
    
    public void populateCourseComboBox() {
        courseComboBox = new JComboBox<>(p.getCourseIdsAsList().toArray(new String[0]));
    }

    public void populateColumnComboBox() {
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
    }

    public static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }

}