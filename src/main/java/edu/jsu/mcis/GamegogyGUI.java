package edu.jsu.mcis;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.*;
import java.awt.Rectangle;
import java.io.*;
import java.util.*;


public class GamegogyGUI extends JFrame implements LeaderboardObserver {
    
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
    private List<Float> columnGrades;
    private List<String> studentIds;

    public String courseSELECTED;
    private String courseId;
    private String topStudentId;

    private Float highGrade;
    private int gradeIndex;
    private int headerIndex;
	
	private Leaderboard lb;
	private List<String> studentNames;
	private List <String> studentEmails;
    
	public GamegogyGUI() {
		initComponents();
	}
	
    private void initComponents() {
        setPreferredSize(new Dimension(1000, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Gamegogy");

        gb = new GradeBook("courses/99000.csv", 1);
        columnGrades = new ArrayList<>(gb.getColumnGrades());
        highGrade = Collections.max(columnGrades);
        gradeIndex = columnGrades.indexOf(highGrade);
        studentIds = gb.getIds();
        topStudentId = studentIds.get(gradeIndex);
        p = new CSVParser(topStudentId);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		add(centerPanel,BorderLayout.CENTER);
        
        termLabel = new JLabel(p.getCourseTerm("99000"));
		termLabel.setName("courseTerm");
        
        enrollmentLabel = new JLabel(p.getEnrollment("99000"));
		enrollmentLabel.setName("courseEnrollment");
        
        idLabel = new JLabel();
		idLabel.setName("studentId");
        idLabel.setText(topStudentId);
        
        nameLabel = new JLabel(p.getStudentName());
		nameLabel.setName("studentName");

        emailLabel = new JLabel(p.getStudentEmail());
		emailLabel.setName("studentEmail");
        
        scoreLabel = new JLabel(Float.toString(highGrade));
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
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox courseComboBox = (JComboBox) event.getSource();
                Object courseSelected = courseComboBox.getSelectedItem();
				courseSELECTED = courseSelected.toString();

				if(courseSELECTED != null) {
					gb = new GradeBook("courses/"+courseSELECTED+".csv", 1);
					columnComboBox.setModel(new DefaultComboBoxModel<>(gb.getColumnHeaders().toArray(new String[0])));
					
					
					columnGrades = new ArrayList<>(gb.getColumnGrades());
					highGrade = Collections.max(columnGrades);
					gradeIndex = columnGrades.indexOf(highGrade);
					
					studentIds = gb.getIds();
					topStudentId = studentIds.get(gradeIndex);
					p = new CSVParser(topStudentId);
					
					termLabel.setText(p.getCourseTerm(courseSELECTED));
					enrollmentLabel.setText(p.getEnrollment(courseSELECTED));

					idLabel.setText(topStudentId);
					nameLabel.setText(p.getStudentName());
					emailLabel.setText(p.getStudentEmail());
					scoreLabel.setText(Float.toString(highGrade));
					
					setLeaderboardData();
					repaint();
				}

				
			}
		});
        
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
		columnComboBox.setName("columnComboBox");
        columnComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				try {
					JComboBox columnComboBox = (JComboBox) event.getSource();
					Object headerSelected = columnComboBox.getSelectedItem();
					List<String> headerList = gb.getColumnHeaders();
					
					headerIndex = headerList.indexOf(headerSelected) + 1;
					gb = new GradeBook("courses/"+courseSELECTED+".csv", headerIndex);
					
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
					
					setLeaderboardData();
					repaint();
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

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
	
		lb = new Leaderboard();
		setLeaderboardData();
		lb.addLeaderboardObserver(this);
		centerPanel.add(lb);
	
        pack();
        setVisible(true);
    }

	private void setLeaderboardData()  {
		Map<String, Float> map = new HashMap<>();
		for(int i = 0; i < columnGrades.size(); i++) {
			map.put(studentIds.get(i), columnGrades.get(i));
		}
		lb.setData(map);
	}
	
	private void setStudentNames() {
		studentNames = new ArrayList<>();
		for(int i = 0; i < columnGrades.size();i++){
			CSVParser p = new CSVParser(studentIds.get(i));
			studentNames.add(p.getStudentName());
		}
	}
	
	public List <String> getStudentNames() {
		setStudentNames();
		return studentNames;
	}
	
	private void setStudentEmails() {
		studentEmails = new ArrayList<>();
		for(int i = 0; i < columnGrades.size();i++){
			CSVParser p = new CSVParser(studentIds.get(i));
			studentEmails.add(p.getStudentEmail());
		}
	}
	
	public List <String> getStudentEmails() {
		setStudentEmails();
		return studentEmails;
	}
	
	public void leaderboardChanged(LeaderboardEvent event) {
		Leaderboard lb = new Leaderboard();
		setStudentNames();
		setStudentEmails(); 
		
		for(int i = 0; i < studentNames.size();i++){
			if(studentIds.get(i) == event.getSelectedId()){
				nameLabel.setText(studentNames.get(i));
				emailLabel.setText(studentEmails.get(i));
			}
			
		}
		idLabel.setText(event.getSelectedId());
		scoreLabel.setText(Float.toString(event.getSelectedGrade()));
		
	}
		
    private static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }
}
